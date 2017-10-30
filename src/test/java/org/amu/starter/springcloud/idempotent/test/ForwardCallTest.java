package org.amu.starter.springcloud.idempotent.test;

import java.util.UUID;

import org.amu.starter.springcloud.idempotent.Constants;
import org.amu.starter.springcloud.idempotent.webapp.IdempotentTestApplication;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = IdempotentInterceptorTests.Application.class)
//@WebAppConfiguration
//@IntegrationTest({"server.port=0"})
//@DirtiesContext
@ContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes=IdempotentTestApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

// 启动IdempotentTestApplication的配置
@Configuration
@EnableAutoConfiguration
public class ForwardCallTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void forwardSameCall() {
		String requestId = "forwardSameCall_:"+UUID.randomUUID();
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add(Constants.REQ_IDEM_ID, requestId);
		headers.add("Content-Type", "application/json");
		HttpEntity requests = new HttpEntity(headers);
		
		ResponseEntity<String> response = restTemplate.exchange("/test/forward/123", HttpMethod.POST, requests,
				String.class);
		String reponse1 = response.getBody();
		
		HttpEntity requests2 = new HttpEntity(headers);
		
		ResponseEntity<String> response2 = restTemplate.exchange("/test/forward/123", HttpMethod.POST, requests2,
				String.class);
		String reponse2 = response2.getBody();
		Assert.assertEquals("The same result", reponse1, reponse2);
	}
	
	@Test
	public void execDifferentCall() {
		String requestId1 = "forwardSameCall_:"+UUID.randomUUID();
		String requestId2 = "forwardSameCall_:"+UUID.randomUUID();

		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add(Constants.REQ_IDEM_ID, requestId1);
		headers.add("Content-Type", "application/json");
		HttpEntity requests = new HttpEntity(headers);
		
		ResponseEntity<String> response = restTemplate.exchange("/test/forward/123", HttpMethod.POST, requests,
				String.class);
		String reponse1 = response.getBody();
		
		MultiValueMap<String, String> headers2 = new LinkedMultiValueMap<String, String>();
		headers.add(Constants.REQ_IDEM_ID, requestId2);
		headers.add("Content-Type", "application/json");
		HttpEntity requests2 = new HttpEntity(headers2);
		
		ResponseEntity<String> response2 = restTemplate.exchange("/test/forward/123", HttpMethod.POST, requests2,
				String.class);
		String reponse2 = response2.getBody();
		System.out.println(reponse1 + "\n" + reponse2);
		Assert.assertNotEquals("The different result", reponse1, reponse2);
	}

}
