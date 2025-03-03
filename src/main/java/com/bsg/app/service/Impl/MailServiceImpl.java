package com.bsg.app.service.Impl;

import com.bsg.app.entities.Account;
import com.bsg.app.entities.Mail;
import com.bsg.app.enums.MailSubTypeEnum;
import com.bsg.app.enums.MailTypeEnum;
import com.bsg.app.enums.ResponseEnum;
import com.bsg.app.exception.SystemErrorException;
import com.bsg.app.model.request.RequestCreateMail;
import com.bsg.app.repository.MailRepository;
import com.bsg.app.service.AccountService;
import com.bsg.app.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final MailRepository mailRepository;
    private final AccountService accountService;

    @Override
    public ResponseEnum createMail(RequestCreateMail req) {
        String accountId = accountService.getCurrentAccountId();
        try {
            Mail mail = Mail.builder()
                    .count(req.getCount())
                    .name(req.getName())
                    .note(req.getNote())
                    .sender(req.getSender())
                    .recipient(req.getRecipient())
                    .subType(req.getSubType())
                    .type(req.getType())
                    .date(req.getDate())
                    .createdBy(accountId)
                    .build();
            mailRepository.save(mail);
            return ResponseEnum.SUCCESS;
        } catch (Exception e) {
            throw new SystemErrorException(e);
        }
    }

    @Override
    public Page<Mail> getListMail(Pageable pageable, MailTypeEnum type, String query, MailSubTypeEnum subType) {
        Page<Mail> mailPage = mailRepository.findAllAndFilter(pageable, type, query, subType);
        List<Mail> mailList = mailPage.getContent();
        List<Mail> responseMailList = new ArrayList<>();
        try {
            for (Mail mail : mailList) {
                Account account = accountService.getCurrentAccount(mail.getCreatedBy());
                mail.setCreatedBy(account!= null ? account.getName() : null);
                responseMailList.add(mail);
            }
            return new PageImpl<>(responseMailList, mailPage.getPageable(), mailPage.getTotalElements());
        } catch (Exception e) {
            throw new SystemErrorException(e);
        }
    }
}
