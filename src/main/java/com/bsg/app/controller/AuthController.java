package com.bsg.app.controller;

import com.bsg.app.anotation.BaseController;
import org.springframework.web.bind.annotation.GetMapping;

@BaseController("auth")

public interface AuthController {
    @GetMapping("ping")
    String ping();
}
