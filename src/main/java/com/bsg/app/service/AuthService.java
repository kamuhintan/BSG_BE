package com.bsg.app.service;

import com.bsg.app.enums.ResponseEnum;
import com.bsg.app.model.request.ReqRegister;
import com.bsg.app.model.request.RequestSignIn;
import com.bsg.app.model.response.ResponseSignIn;

public interface AuthService {

    ResponseEnum registerSuperAdmin(ReqRegister reqRegister);

    ResponseSignIn signIn(RequestSignIn req);
}
