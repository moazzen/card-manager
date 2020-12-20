package com.challenge.packages.bank.card.services;

import com.challenge.config.Constants;
import com.challenge.packages.bank.card.constants.HttpStatusResponse;
import com.challenge.packages.bank.card.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class CardServices {

    private final CardServiceInterface cardService;

    @Autowired
    public CardServices(CardServiceInterface cardService) {
        this.cardService = cardService;
    }


    ////////////////////////////////////////////////////////////////////////
    /////////////////////////////// Add Card ///////////////////////////////
    ////////////////////////////////////////////////////////////////////////

    @PostMapping (value = Constants.BANK_CARD_ADD_SERVICE)
    public ResponseEntity<CardResponse> AddCard (@RequestBody Card card) {

        //todo verifying request ...

        CardResponse cardResponse = new CardResponse(HttpStatusResponse.SUCCESS.getStatusCode(), HttpStatusResponse.SUCCESS.getDescription());

        try {
            cardService.addCard(card);
            return ResponseEntity.ok().body(cardResponse);
        } catch (Exception e) {
            cardResponse.setStatus(HttpStatusResponse.INTERNAL_ERROR.getStatusCode());
            cardResponse.setDescription(HttpStatusResponse.INTERNAL_ERROR.getDescription());
            return ResponseEntity.ok().body(cardResponse);
        }
    }

    ///////////////////////////////////////////////////////////////////////
    //////////////////////////// Get Card List ////////////////////////////
    ///////////////////////////////////////////////////////////////////////

    @PostMapping (value = Constants.BANK_CARD_LIST_SERVICE)
    public ResponseEntity<CardListResponse> getCardList (@RequestBody Card card) {

        //todo verifying request ...

        CardListResponse cardListResponse = new CardListResponse(HttpStatusResponse.SUCCESS.getStatusCode(), HttpStatusResponse.SUCCESS.getDescription());

        try {
            List<Card> cardsByCustomerId = cardService.getCardsByCustomerId(card.getCustomerId());
            cardListResponse.setCardList(cardsByCustomerId);
            return ResponseEntity.ok().body(cardListResponse);
        } catch (Exception e) {
            cardListResponse.setStatus(HttpStatusResponse.INTERNAL_ERROR.getStatusCode());
            cardListResponse.setDescription(HttpStatusResponse.INTERNAL_ERROR.getDescription());
            return ResponseEntity.ok().body(cardListResponse);
        }
    }

    ///////////////////////////////////////////////////////////////////////
    ///////////////////////////// Remove Card /////////////////////////////
    ///////////////////////////////////////////////////////////////////////

    @PostMapping (value = Constants.BANK_CARD_REMOVE_SERVICE)
    public ResponseEntity<CardResponse> removeCard (@RequestBody Card card) {

        //todo verifying request ...

        CardResponse cardResponse = new CardResponse(HttpStatusResponse.SUCCESS.getStatusCode(), HttpStatusResponse.SUCCESS.getDescription());

        try {
            cardService.deleteCardByPanAndCustomerId(card.getPan(), card.getCustomerId());
            return ResponseEntity.ok().body(cardResponse);
        } catch (Exception e) {
            cardResponse.setStatus(HttpStatusResponse.INTERNAL_ERROR.getStatusCode());
            cardResponse.setDescription(HttpStatusResponse.INTERNAL_ERROR.getDescription());
            return ResponseEntity.ok().body(cardResponse);
        }
    }

    ////////////////////////////////////////////////////////////////////////
    /////////////////////////////// Transfer ///////////////////////////////
    ////////////////////////////////////////////////////////////////////////


    @PostMapping (value = Constants.BANK_CARD_TRANSFER_SERVICE)
    public ResponseEntity<CardResponse> transfer (@RequestBody Transfer transfer) {

        //todo verifying request ...

        CardResponse cardResponse = new CardResponse(HttpStatusResponse.SUCCESS.getStatusCode(), HttpStatusResponse.SUCCESS.getDescription());
        Transfer response = null;
        try {
            response = cardService.addTransfer(transfer);
            if (response.getHttpResponseCode() == HttpStatus.OK.value())
                return ResponseEntity.ok().body(cardResponse);
            else {
                cardResponse.setDescription("Error");
                return ResponseEntity.status(transfer.getHttpResponseCode()).body(cardResponse);
            }
        } catch (Exception e) {
            HttpStatusResponse internalError = HttpStatusResponse.INTERNAL_ERROR;
            cardResponse.setStatus(internalError.getStatusCode());
            cardResponse.setDescription(internalError.getDescription());
            return ResponseEntity.status(internalError.getHttpCode()).body(cardResponse);
        }
    }

    ////////////////////////////////////////////////////////////////////////
    ///////////////////////// Get Transfer History /////////////////////////
    ////////////////////////////////////////////////////////////////////////

    @PostMapping (value = Constants.BANK_CARD_TRANSFER_HISTORY_SERVICE)
    public ResponseEntity<CustomerTransactionCount> getTransferHistory (@RequestBody TransferHistoryRequest historyRequest) {

        //todo verifying request ...

        CustomerTransactionCount customerTransactionCount = new CustomerTransactionCount(HttpStatusResponse.SUCCESS.getStatusCode(), HttpStatusResponse.SUCCESS.getDescription());
        customerTransactionCount.setCustomerId(historyRequest.getCustomerId());

        try {
            List<CardTransactions> allTransfersByCustomerIdAndInsertDate = cardService.getAllTransfersByCustomerIdAndInsertDate(historyRequest.getCustomerId(),
                    LocalDate.parse(historyRequest.getFromDate()),
                    LocalDate.parse(historyRequest.getToDate()));
            customerTransactionCount.setPanTransactions(allTransfersByCustomerIdAndInsertDate);

            return ResponseEntity.ok().body(customerTransactionCount);
        } catch (Exception e) {
            customerTransactionCount.setStatus(HttpStatusResponse.INTERNAL_ERROR.getStatusCode());
            customerTransactionCount.setDescription(HttpStatusResponse.INTERNAL_ERROR.getDescription());
            return ResponseEntity.ok().body(customerTransactionCount);
        }
    }
}
