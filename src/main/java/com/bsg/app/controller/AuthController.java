package com.bsg.app.controller;

import com.bsg.app.anotation.BaseController;
import com.bsg.app.model.request.ReqRegister;
import com.bsg.app.model.request.RequestSignIn;
import com.bsg.app.model.response.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@BaseController("auth")

public interface AuthController {

    @PostMapping("v1/register")
    BaseResponse registerSuperAdmin(@RequestBody ReqRegister reqRegister);

    @PostMapping("v1/sign-in")
    BaseResponse signIn(@RequestBody RequestSignIn req);
    @GetMapping("ping")
    String ping();
}

