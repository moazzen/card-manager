package com.challenge;

import com.challenge.config.Constants;
import com.challenge.packages.bank.card.dao.CustomerDao;
import com.challenge.packages.bank.card.dao.TransferDao;
import com.challenge.packages.bank.card.dto.*;
import com.challenge.packages.bank.card.services.CardServiceInterface;

import com.challenge.packages.bank.general.services.RabbitmqService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;


import static com.github.tomakehurst.wiremock.client.WireMock.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {

	@LocalServerPort
	int randomServerPort;
	@Autowired
	private CardServiceInterface cardService;
	@Autowired
	private RestTemplate restTemplate;

	/************************************/
	/********** WireMock SetUp **********/
	/************************************/

	private static WireMockServer transferService_1 = new WireMockServer(Constants.TRANSFER_PORT_1);
	private static WireMockServer transferService_2 = new WireMockServer(Constants.TRANSFER_PORT_2);
	private static WireMockServer smsProvider_1 = new WireMockServer(Constants.SMS_PROVIDER_PORT_1);

	@BeforeAll
	static void init () {
		transferService_1.start();
		configureFor(Constants.TRANSFER_HOST_1, Constants.TRANSFER_PORT_1);

		transferService_2.start();
		configureFor(Constants.TRANSFER_HOST_2, Constants.TRANSFER_PORT_2);

		smsProvider_1.start();
		configureFor(Constants.SMS_PROVIDER_HOST_1, Constants.SMS_PROVIDER_PORT_1);
	}
	@AfterAll
	static void finalizing () {
		transferService_1.stop();
		transferService_2.stop();
		smsProvider_1.stop();
	}

	////////////////////////////////////////
	/////////////// Add Card ///////////////
	////////////////////////////////////////
	@Test
	void addCard () {
		Card card = new Card();
		card.setPan(6104336525952888L);
		card.setCustomerId(1);

		HttpEntity<Card> request = new HttpEntity<>(card, new HttpHeaders());
		ResponseEntity<CardResponse> exchange = restTemplate.exchange(getUrl(Constants.BANK_CARD_ADD_SERVICE), HttpMethod.POST, request, new ParameterizedTypeReference<CardResponse>() {});

		System.out.println(exchange.getStatusCode());
		System.out.println(exchange.getBody().toString());

	}

	/////////////////////////////////////////
	//////// Get Cards By CustomerId ////////
	/////////////////////////////////////////


	@Test
	void findAllByCustomerId() {

		Card card = new Card();
		card.setCustomerId(1);

		HttpEntity<Card> request = new HttpEntity<>(card, new HttpHeaders());
		ResponseEntity<CardListResponse> exchange = restTemplate.exchange(getUrl(Constants.BANK_CARD_LIST_SERVICE), HttpMethod.POST, request, new ParameterizedTypeReference<CardListResponse>() {});

		System.out.println(exchange.getStatusCode());
		System.out.println(exchange.getBody().toString());
	}

	/////////////////////////////////////////
	////////////// Remove Card //////////////
	/////////////////////////////////////////

	@Test
	void deleteCardByPanAndCustomerId() {
		Card card = new Card();
		card.setCustomerId(1);
		card.setPan(6104336525951278L);

		HttpEntity<Card> request = new HttpEntity<>(card, new HttpHeaders());
		ResponseEntity<CardResponse> exchange = restTemplate.exchange(getUrl(Constants.BANK_CARD_REMOVE_SERVICE), HttpMethod.POST, request, new ParameterizedTypeReference<CardResponse>() {});

		System.out.println(exchange.getStatusCode());
		System.out.println(exchange.getBody().toString());
	}

	////////////////////////////////////////
	/////////////// Transfer ///////////////
	////////////////////////////////////////

	@Test
	void transfer () {
		Transfer transfer = new Transfer();
		transfer.setCustomerId(1);
		transfer.setFromPan(6037337564522871L);
		transfer.setToPan(6037564521569332L);
		transfer.setCvv2("200");
		transfer.setPin("454211");
		transfer.setExpDateMonth("12");
		transfer.setExpDateYear("99");
		transfer.setAmount(100_000);

		//create dummy services for service providers
		transferService_1.stubFor(
				WireMock.post(urlEqualTo(Constants.TRANSFER_URL_1))
						.willReturn(aResponse()
								.withStatus(200)
								.withHeader("Content-Type", "text/plain")
								.withBody("Hello from provider 1!")));

		transferService_2.stubFor(
				WireMock.post(urlEqualTo(Constants.TRANSFER_URL_2))
						.willReturn(aResponse()
								.withStatus(200)
								.withHeader("Content-Type", "text/plain")
								.withBody("Hello from provider 2")));
		smsProvider_1.stubFor(
				WireMock.post(urlEqualTo(Constants.SMS_PROVIDER_URL_1))
						.willReturn(aResponse()
								.withStatus(200)
								.withHeader("Content-Type", "text/plain")
								.withBody("Hello from SMS provider 1")));


		HttpEntity<Transfer> request = new HttpEntity<>(transfer, new HttpHeaders());
		ResponseEntity<CardResponse> exchange = restTemplate.exchange(getUrl(Constants.BANK_CARD_TRANSFER_SERVICE), HttpMethod.POST, request, new ParameterizedTypeReference<CardResponse>() {});

		System.out.println(exchange.getStatusCode());
		System.out.println(exchange.getBody().toString());

	}

	////////////////////////////////////////
	///////// Get Transfer History /////////
	////////////////////////////////////////

	@Test
	void getTransfersHistory() {

		TransferHistoryRequest transferHistoryRequest = new TransferHistoryRequest(1, "2020-12-15", "2020-12-18");

		HttpEntity<TransferHistoryRequest> request = new HttpEntity<>(transferHistoryRequest, new HttpHeaders());
		ResponseEntity<CustomerTransactionCount> exchange = restTemplate.exchange(getUrl(Constants.BANK_CARD_TRANSFER_HISTORY_SERVICE), HttpMethod.POST, request, new ParameterizedTypeReference<CustomerTransactionCount>() {});

		System.out.println(exchange.getStatusCode());
		System.out.println(exchange.getBody().toString());
	}


	private String getUrl (String serviceUrl) {
		return "http://localhost:" + randomServerPort + serviceUrl;
	}
}
