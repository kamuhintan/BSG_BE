package com.bsg.app.controller.impl;

import com.bsg.app.anotation.BaseController;
import com.bsg.app.anotation.BaseControllerImpl;
import com.bsg.app.controller.AuthController;
@BaseControllerImpl
public class AuthControllerImpl implements AuthController {
    @Override
    public String ping() {
        return "pow";
    }
}
