package com.bsg.app.controller.impl;

import com.bsg.app.anotation.BaseController;
import com.bsg.app.anotation.BaseControllerImpl;
import com.bsg.app.controller.AuthController;
import com.bsg.app.model.request.ReqRegister;
import com.bsg.app.model.request.RequestSignIn;
import com.bsg.app.model.response.BaseResponse;
import com.bsg.app.service.AuthService;
import com.bsg.app.utilities.ResponseHelper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@BaseControllerImpl
public class AuthControllerImpl implements AuthController {
    private final AuthService authService;
    @Override
    public BaseResponse registerSuperAdmin(ReqRegister reqRegister) {
        return ResponseHelper.createBaseResponse(authService.registerSuperAdmin(reqRegister));
    }

    @Override
    public BaseResponse signIn(RequestSignIn req) {
        return ResponseHelper.createBaseResponse(authService.signIn(req));
    }

    @Override
    public String ping() {
        return "pow";
    }
}
