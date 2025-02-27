package com.bsg.app.controller.impl;

import com.bsg.app.anotation.BaseControllerImpl;
import com.bsg.app.controller.CreditController;
import com.bsg.app.model.request.ReqCreateCreditConsumer;
import com.bsg.app.model.request.ReqCreatePkKur;
import com.bsg.app.model.request.RequestCreateBiChecking;
import com.bsg.app.model.request.RequestCreateCreditCommercial;
import com.bsg.app.model.response.BaseResponse;
import com.bsg.app.service.CreditService;
import com.bsg.app.utilities.ResponseHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

@BaseControllerImpl
@RequiredArgsConstructor
public class CreditControllerImpl implements CreditController {

    private final CreditService creditService;

    @Override
    public BaseResponse createConsumerCredit(ReqCreateCreditConsumer req) {
        return ResponseHelper.createBaseResponse(creditService.createConsumerCredit(req));
    }

    @Override
    public BaseResponse createCommercialCredit(RequestCreateCreditCommercial req) {
        return ResponseHelper.createBaseResponse(creditService.createCommercialCredit(req));
    }

    @Override
    public BaseResponse createBiChecking(RequestCreateBiChecking req) {
        return ResponseHelper.createBaseResponse(creditService.createBiChecking(req));
    }

    @Override
    public BaseResponse createPkKur(ReqCreatePkKur req) {
        return ResponseHelper.createBaseResponse(creditService.createPkKur(req));
    }

    @Override
    public BaseResponse getListConsumerCredit(Pageable pageable, String query, String type, String subType) {

        return ResponseHelper.createBaseResponse(creditService.getListConsumerCredit(pageable, query, type, subType));
    }

    @Override
    public BaseResponse getListCommercialCredit(Pageable pageable, String query) {
        return ResponseHelper.createBaseResponse(creditService.getListCommercialCredit(pageable, query));
    }

    @Override
    public BaseResponse getListBiChecking(Pageable pageable, String query) {
        return ResponseHelper.createBaseResponse(creditService.getListBiChecking(pageable, query));

    }

    @Override
    public BaseResponse getListPkKur(Pageable pageable, String query) {

        return ResponseHelper.createBaseResponse(creditService.getListPkKur(pageable, query));
    }

    @Override
    public BaseResponse getSummaryCredit() {
        return ResponseHelper.createBaseResponse(creditService.getSummary());
    }
}

