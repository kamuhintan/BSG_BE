package com.bsg.app.controller;

import com.bsg.app.anotation.BaseController;
import com.bsg.app.model.request.RequestChangePassword;
import com.bsg.app.model.request.RequestCreateAccount;
import com.bsg.app.model.response.BaseResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@BaseController("account")
public interface AccountController {

    @PostMapping("v1/create-account")
    BaseResponse createNewAccount(@RequestBody RequestCreateAccount req);

    @GetMapping("v1/list")
    BaseResponse listAccounts(
            Pageable pageable,
            @RequestParam(value = "query", required = false) String query
    );

    @PatchMapping("v1/reset-password/{id}")
    BaseResponse resetPassword(@PathVariable String id);

    @PutMapping("v1/change-password")
    BaseResponse changePassword(@RequestBody RequestChangePassword req);

    @DeleteMapping("v1/delete/{id}")
    BaseResponse delete(@PathVariable String id);

    @GetMapping("v1/get-me")
    BaseResponse getMe();
}

