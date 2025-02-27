package com.bsg.app.service;

import com.bsg.app.entities.Mail;
import com.bsg.app.enums.MailSubTypeEnum;
import com.bsg.app.enums.MailTypeEnum;
import com.bsg.app.enums.ResponseEnum;
import com.bsg.app.model.request.RequestCreateMail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MailService {
    ResponseEnum createMail(RequestCreateMail req);

    Page<Mail> getListMail(Pageable pageable, MailTypeEnum type, String query, MailSubTypeEnum subType);
}


