package com.bsg.app.controller;

import com.bsg.app.anotation.BaseController;
import com.bsg.app.model.request.ReqCreateCreditConsumer;
import com.bsg.app.model.request.ReqCreatePkKur;
import com.bsg.app.model.request.RequestCreateBiChecking;
import com.bsg.app.model.request.RequestCreateCreditCommercial;
import com.bsg.app.model.response.BaseResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@BaseController("credit")
public interface CreditController {

    @PostMapping("v1/new/consumer")
    BaseResponse createConsumerCredit(@RequestBody ReqCreateCreditConsumer req);

    @PostMapping("v1/new/commercial")
    BaseResponse createCommercialCredit(@RequestBody RequestCreateCreditCommercial req);

    @PostMapping("v1/new/bi-checking")
    BaseResponse createBiChecking(@RequestBody RequestCreateBiChecking req);

    @PostMapping("v1/new/pk-kur")
    BaseResponse createPkKur(@RequestBody ReqCreatePkKur req);

    @GetMapping("v1/list/consumer")
    BaseResponse getListConsumerCredit(
            Pageable pageable,
            @RequestParam(value = "query", required = false) String query,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "sub_type", required = false) String subType
    );

    @GetMapping("v1/list/commercial")
    BaseResponse getListCommercialCredit(
            Pageable pageable,
            @RequestParam(value = "query", required = false) String query
    );

    @GetMapping("v1/list/bi-checking")
    BaseResponse getListBiChecking(
            Pageable pageable,
            @RequestParam(value = "query", required = false) String query
    );

    @GetMapping("v1/list/pk-kur")
    BaseResponse getListPkKur(
            Pageable pageable,
            @RequestParam(value = "query", required = false) String query
    );

    @GetMapping("v1/summary")
    BaseResponse getSummaryCredit();

}

