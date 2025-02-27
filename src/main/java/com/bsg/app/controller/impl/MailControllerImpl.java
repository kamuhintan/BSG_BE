package com.bsg.app.controller.impl;

import com.bsg.app.anotation.BaseControllerImpl;
import com.bsg.app.controller.MailController;
import com.bsg.app.enums.MailSubTypeEnum;
import com.bsg.app.enums.MailTypeEnum;
import com.bsg.app.model.request.RequestCreateMail;
import com.bsg.app.model.response.BaseResponse;
import com.bsg.app.service.MailService;
import com.bsg.app.utilities.ResponseHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;


@BaseControllerImpl
@RequiredArgsConstructor
public class MailControllerImpl implements MailController {
    private final MailService mailService;
    @Override
    public BaseResponse createMail(RequestCreateMail req) {
        return ResponseHelper.createBaseResponse(mailService.createMail(req));
    }

    @Override
    public BaseResponse getListMail(Pageable pageable, MailTypeEnum type, String query, MailSubTypeEnum subType) {
        return ResponseHelper.createBaseResponse(mailService.getListMail(pageable, type, query, subType));
    }
}

