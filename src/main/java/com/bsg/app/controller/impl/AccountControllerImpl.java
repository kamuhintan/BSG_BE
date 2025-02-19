package com.bsg.app.controller.impl;

import com.bsg.app.anotation.BaseControllerImpl;
import com.bsg.app.controller.AccountController;
import com.bsg.app.model.request.RequestChangePassword;
import com.bsg.app.model.request.RequestCreateAccount;
import com.bsg.app.model.response.BaseResponse;
import com.bsg.app.service.AccountService;
import com.bsg.app.utilities.ResponseHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;


@BaseControllerImpl
@RequiredArgsConstructor
public class AccountControllerImpl implements AccountController {

    private final AccountService accountService;

    @Override
    public BaseResponse createNewAccount(RequestCreateAccount req) {
        return ResponseHelper.createBaseResponse(accountService.createNewAccount(req));
    }

    @Override
    public BaseResponse listAccounts(Pageable pageable, String query) {

        return ResponseHelper.createBaseResponse(accountService.getListAccount(pageable, query));
    }

    @Override
    public BaseResponse resetPassword(String id) {
        return ResponseHelper.createBaseResponse(accountService.resetPassword(id));
    }

    @Override
    public BaseResponse changePassword(RequestChangePassword req) {
        return ResponseHelper.createBaseResponse(accountService.changePassword(req));
    }

    @Override
    public BaseResponse delete(String id) {
        return ResponseHelper.createBaseResponse(accountService.deleteAccount(id));
    }

    @Override
    public BaseResponse getMe() {
        return ResponseHelper.createBaseResponse(accountService.getMe());
    }


}

