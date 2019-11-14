package com.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

import com.demo.model.MessageInfo;
import com.demo.repository.DynamoDbRepository;
import com.demo.model.TaskResponse;

@RestController
public class DynamoDbController {
	private static final Logger LOGGER = LoggerFactory.getLogger(DynamoDbController.class);

	@Autowired
	private DynamoDbRepository repository;

	@GetMapping("/test-response")
	public TaskResponse doTestResponse(){
		return new TaskResponse("Test Response Ok");
	}

	@PostMapping(value = "/message")
	public ResponseEntity<TaskResponse> doPostInsertIntoDB(@RequestBody MessageInfo messageInfo) {
		String currentTime = Instant.now().toString();
		LOGGER.info("Received request: {} at {}", messageInfo.toString(), currentTime);
		messageInfo.setDateSent(currentTime);
		try {
			repository.insertMessage(messageInfo);
			TaskResponse taskResponse = new TaskResponse("Successfully inserted into DynamoDB table");
			taskResponse.AddtoData(repository.getMessage(messageInfo.getRecipient(), messageInfo.getDateSent()));
			return new ResponseEntity<TaskResponse>(taskResponse, HttpStatus.OK);
		} catch (Exception e) {
			TaskResponse taskResponse = new TaskResponse("Insert Failed: " + e.toString());
			return new ResponseEntity<TaskResponse>(taskResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(value = "/message") // <url>:<port>/message?recipient=<recipient>
	public ResponseEntity<TaskResponse> doGetListAllMessageForRecipient(@RequestParam String recipient, @RequestParam(required = false) String date){
		LOGGER.info("Request params:  " + recipient + " , date: " + date);
		List<MessageInfo> requestedMessageList;
		TaskResponse taskResponse = new TaskResponse();
		try {
			if (date == null){
				LOGGER.info("Search only by recipient: " + recipient);
				requestedMessageList = repository.queryByRecipient(recipient);
			} else {
				LOGGER.info("Search by recipient: " + recipient + " and date: " + date);
				requestedMessageList = repository.queryByRecipientAndDate(recipient, date);
			}
			
			if (requestedMessageList.size() > 0){
				for (MessageInfo msg : requestedMessageList){
					taskResponse.AddtoData(msg);
				}
				taskResponse.setMessage("Search Successful: found a total of " + requestedMessageList.size() + " records");
				return new ResponseEntity<TaskResponse>(taskResponse, HttpStatus.OK);
			}
			taskResponse.setMessage("Search Failed: No messages found");
			return new ResponseEntity<TaskResponse>(taskResponse, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			taskResponse.setMessage("Search Failed: " + e.toString());
			return new ResponseEntity<TaskResponse>(taskResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}