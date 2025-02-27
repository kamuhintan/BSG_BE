package com.bsg.app.controller;

import com.bsg.app.anotation.BaseController;
import com.bsg.app.enums.MailSubTypeEnum;
import com.bsg.app.enums.MailTypeEnum;
import com.bsg.app.model.request.RequestCreateMail;
import com.bsg.app.model.response.BaseResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@BaseController("mail")
public interface MailController {

    @PostMapping("v1/create")
    BaseResponse createMail(@RequestBody RequestCreateMail req);

    @GetMapping("v1/list")
    BaseResponse getListMail(Pageable pageable,
                             @RequestParam(value = "type", required = false) MailTypeEnum type,
                             @RequestParam(value = "query", required = false) String query,
                             @RequestParam(value = "sub_type", required = false) MailSubTypeEnum subType

    );
}