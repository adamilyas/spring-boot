package com.example.demo;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DemoApplication {

	static Logger log = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		String url = "http://localhost:5000";

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		String result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();

		log.info( "Result of Rest Exchange : " + result);
		SpringApplication.run(DemoApplication.class, args);
	}
}
