package com.demo.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.demo.model.MessageInfo;;

@Repository
public class DynamoDbRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(DynamoDbRepository.class);

	@Autowired
	private DynamoDBMapper mapper;

	public void insertMessage(MessageInfo messageInfo) {
		mapper.save(messageInfo);
	}

	public MessageInfo getMessage(String recipient, String date) {
		return mapper.load(MessageInfo.class, recipient, date);
	}

	public List<MessageInfo> queryByRecipient(String recipient){
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":val1", new AttributeValue().withS(recipient));
		
		DynamoDBQueryExpression<MessageInfo> queryExpression = new DynamoDBQueryExpression<MessageInfo>()
			.withKeyConditionExpression("recipient = :val1")
			.withExpressionAttributeValues(eav);

		List<MessageInfo> queryResult = mapper.query(MessageInfo.class, queryExpression);
		return queryResult;
	}

	public List<MessageInfo> queryByRecipientAndDate(String recipient, String dateSent){
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":val1", new AttributeValue().withS(recipient));
		eav.put(":val2", new AttributeValue().withS(dateSent));
		
		DynamoDBQueryExpression<MessageInfo> queryExpression = new DynamoDBQueryExpression<MessageInfo>()
			.withKeyConditionExpression("recipient = :val1 AND begins_with(dateSent, :val2)")
			.withExpressionAttributeValues(eav);

		List<MessageInfo> queryResult = mapper.query(MessageInfo.class, queryExpression);
		return queryResult;
	}
}