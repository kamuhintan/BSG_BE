package com.bsg.app.service;

import com.bsg.app.enums.ResponseEnum;
import com.bsg.app.model.request.ReqCreateCreditConsumer;
import com.bsg.app.model.request.ReqCreatePkKur;
import com.bsg.app.model.request.RequestCreateBiChecking;
import com.bsg.app.model.request.RequestCreateCreditCommercial;
import com.bsg.app.model.response.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CreditService {
    String createConsumerCredit(ReqCreateCreditConsumer req);

    ResponseEnum createCommercialCredit(RequestCreateCreditCommercial req);

    ResponseEnum createBiChecking(RequestCreateBiChecking req);

    Page<ResponseCreditConsumer> getListConsumerCredit(Pageable pageable, String query, String type, String subType);

    Page<ResponseCreditCommercial> getListCommercialCredit(Pageable pageable, String query);

    Page<ResponseListBiChecking> getListBiChecking(Pageable pageable, String query);

    ResponseSummary getSummary();

    Page<ResponsePkKur> getListPkKur(Pageable pageable, String query);

    ResponseEnum  createPkKur(ReqCreatePkKur req);
}

