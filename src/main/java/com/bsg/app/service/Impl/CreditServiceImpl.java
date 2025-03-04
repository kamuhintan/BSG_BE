package com.bsg.app.service.Impl;

import com.bsg.app.entities.Account;
import com.bsg.app.entities.Credit;
import com.bsg.app.enums.CreditTypeEnum;
import com.bsg.app.enums.MailTypeEnum;
import com.bsg.app.enums.ResponseEnum;
import com.bsg.app.exception.BadRequestException;
import com.bsg.app.exception.SystemErrorException;
import com.bsg.app.model.request.ReqCreateCreditConsumer;
import com.bsg.app.model.request.ReqCreatePkKur;
import com.bsg.app.model.request.RequestCreateBiChecking;
import com.bsg.app.model.request.RequestCreateCreditCommercial;
import com.bsg.app.model.response.*;
import com.bsg.app.repository.CreditRepository;
import com.bsg.app.repository.MailRepository;
import com.bsg.app.service.AccountService;
import com.bsg.app.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.web.access.WebInvocationPrivilegeEvaluator;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreditServiceImpl implements CreditService {

    private final AccountService accountService;
    private final CreditRepository creditRepository;
    private final MailRepository mailRepository;
    private final WebInvocationPrivilegeEvaluator privilegeEvaluator;

    @Override
    public String createConsumerCredit(ReqCreateCreditConsumer req) {
        checkPhoneNumber(req.getNumber(), CreditTypeEnum.CONSUMER);

        try {

            Credit credit = Credit.builder()
                    .name(req.getName())
                    .date(req.getDate())
                    .insurance(req.getInsurance())
                    .plafond(req.getPlafond())
                    .agency(req.getAgency())
                    .phone(req.getPhoneNumber())
                    .address(req.getAddress())
                    .type(CreditTypeEnum.CONSUMER)
                    .consumerCreditType(req.getConsumerCreditType())
                    .consumerCreditSubType(req.getConsumerCreditSubType())
                    .number(req.getNumber())
                    .createdBy(accountService.getCurrentAccountId())
                    .pkNumber(req.getPkNumber())
                    .build();
            creditRepository.save(credit);
            return ResponseEnum.SUCCESS.name();
        } catch (Exception e) {
            throw new SystemErrorException(e);
        }
    }

    @Override
    public ResponseEnum createCommercialCredit(RequestCreateCreditCommercial req) {
        checkPhoneNumber(req.getNumber(), CreditTypeEnum.COMMERCIAL);

        try {

            Credit credit = Credit.builder()
                    .name(req.getName())
                    .date(req.getDate())
                    .insurance(req.getAssurance())
                    .businessType(req.getBusinessType())
                    .requestNumber(req.getRequestNumber())
                    .pkDate(req.getPkDate())
                    .number(req.getNumber())
                    .plafond(req.getPlafond())
                    .type(CreditTypeEnum.COMMERCIAL)
                    .creditType(req.getCreditType())
                    .createdBy(accountService.getCurrentAccountId())
                    .build();
            creditRepository.save(credit);
            return ResponseEnum.SUCCESS;
        } catch (Exception e) {
            throw new SystemErrorException(e);
        }
    }

    @Override
    public ResponseEnum createBiChecking(RequestCreateBiChecking req) {
        checkPhoneNumber(req.getNumber(), CreditTypeEnum.BI_CHECKING);

        try {
            Credit credit = Credit.builder()
                    .name(req.getName())
                    .number(req.getNumber())
                    .requestDate(req.getRequestDate())
                    .ktpNumber(req.getKtpNumber())
                    .type(CreditTypeEnum.BI_CHECKING)
                    .placeOfBirth(req.getPlaceOfBirth())
                    .dateOfBirth(req.getDateOfBirth())
                    .objective(req.getObjective())
                    .note(req.getNote())
                    .createdBy(accountService.getCurrentAccountId())
                    .build();
            creditRepository.save(credit);
            return ResponseEnum.SUCCESS;
        } catch (Exception e) {
            throw new SystemErrorException(e);
        }
    }

    @Override
    public Page<ResponseCreditConsumer> getListConsumerCredit(Pageable pageable, String query, String type, String subType) {
        try {
            Page<Credit> creditPage = creditRepository.findAllAndFilter(pageable, query, type, CreditTypeEnum.CONSUMER, subType);
            List<Credit> creditList = creditPage.getContent();
            List<ResponseCreditConsumer> responseCreditConsumers = new ArrayList<>();
            for (Credit credit : creditList) {
                Optional<Account> createdByAccount = Optional.ofNullable(accountService.getCurrentAccount(credit.getCreatedBy()));
                ResponseCreditConsumer responseCreditConsumer = ResponseCreditConsumer.builder()
                        .id(credit.getId())
                        .name(credit.getName())
                        .date(credit.getDate())
                        .insurance(credit.getInsurance())
                        .plafond(credit.getPlafond())
                        .agency(credit.getAgency())
                        .number(credit.getNumber())
                        .phone(credit.getPhone())
                        .address(credit.getAddress())
                        .type(credit.getType())
                        .consumerCreditType(credit.getConsumerCreditType())
                        .consumerCreditSubType(credit.getConsumerCreditSubType())
                        .number(credit.getNumber())
                        .createdDate(credit.getCreatedDate())
                        .pkNumber(credit.getPkNumber())
                        .build();
                createdByAccount.ifPresent(value -> responseCreditConsumer.setCreatedBy(value.getName()));
                responseCreditConsumers.add(responseCreditConsumer);

            }
            return new PageImpl<>(responseCreditConsumers, creditPage.getPageable(), creditPage.getTotalElements());

        } catch (Exception e) {
            throw new SystemErrorException(e);
        }
    }

    @Override
    public Page<ResponseCreditCommercial> getListCommercialCredit(Pageable pageable, String query) {
        try {
            Page<Credit> creditPage = creditRepository.findAllAndFilter(pageable, query, CreditTypeEnum.COMMERCIAL);
            List<Credit> creditList = creditPage.getContent();
            List<ResponseCreditCommercial> responseCreditConsumers = new ArrayList<>();
            for (Credit credit : creditList) {
                Optional<Account> createdByAccount = Optional.ofNullable(accountService.getCurrentAccount(credit.getCreatedBy()));
                ResponseCreditCommercial responseCreditConsumer = ResponseCreditCommercial.builder()
                        .id(credit.getId())
                        .name(credit.getName())
                        .date(credit.getDate())
                        .insurance(credit.getInsurance())
                        .businessType(credit.getBusinessType())
                        .requestNumber(credit.getRequestNumber())
                        .pkDate(credit.getPkDate())
                        .plafond(credit.getPlafond())
                        .number(credit.getNumber())
                        .creditType(credit.getCreditType())
                        .createdDate(credit.getCreatedDate())
                        .type(credit.getType())
                        .build();
                createdByAccount.ifPresent(value -> responseCreditConsumer.setCreatedBy(value.getName()));
                responseCreditConsumers.add(responseCreditConsumer);

            }
            return new PageImpl<>(responseCreditConsumers, creditPage.getPageable(), creditPage.getTotalElements());

        } catch (Exception e) {
            throw new SystemErrorException(e);
        }
    }

    @Override
    public Page<ResponseListBiChecking> getListBiChecking(Pageable pageable, String query) {
        try {
            Page<Credit> creditPage = creditRepository.findAllAndFilter(pageable, query, CreditTypeEnum.BI_CHECKING);
            List<Credit> creditList = creditPage.getContent();
            List<ResponseListBiChecking> responseBiCheckings = new ArrayList<>();
            for (Credit credit : creditList) {
                Optional<Account> createdByAccount = Optional.ofNullable(accountService.getCurrentAccount(credit.getCreatedBy()));
                ResponseListBiChecking response = ResponseListBiChecking.builder()
                        .id(credit.getId())
                        .name(credit.getName())
                        .requestDate(credit.getRequestDate())
                        .number(credit.getNumber())
                        .ktpNumber(credit.getKtpNumber())
                        .dateOfBirth(credit.getDateOfBirth())
                        .placeOfBirth(credit.getPlaceOfBirth())
                        .objective(credit.getObjective())
                        .createdDate(credit.getCreatedDate())
                        .note(credit.getNote())
                        .type(credit.getType())
                        .build();
                createdByAccount.ifPresent(value -> response.setCreatedBy(value.getName()));
                responseBiCheckings.add(response);

            }
            return new PageImpl<>(responseBiCheckings, creditPage.getPageable(), creditPage.getTotalElements());

        } catch (Exception e) {
            throw new SystemErrorException(e);
        }
    }

    @Override
    public ResponseSummary getSummary() {
        try {
            BigInteger totalSumPlafondConsumer = creditRepository.sumTotalPlafondConsumer(CreditTypeEnum.CONSUMER);
            BigInteger totalSumPlafondCommercial = creditRepository.sumTotalPlafondConsumer(CreditTypeEnum.COMMERCIAL);
            BigInteger totalSumPlafondPkKur = creditRepository.sumTotalPlafondConsumer(CreditTypeEnum.PK_KUR);
            BigInteger totalCountIncomingMail = mailRepository.countAllByType(MailTypeEnum.INCOMING);
            BigInteger totalCountOutgoing = mailRepository.countAllByType(MailTypeEnum.OUTGOING);
            BigInteger totalCommercial = totalSumPlafondCommercial.add(totalSumPlafondPkKur);
            return ResponseSummary.builder()
                    .totalPlafondConsumer(totalSumPlafondConsumer)
                    .totalPlafondCommercial(totalCommercial)
                    .totalIncomingMail(totalCountIncomingMail)
                    .totalOutgoingMail(totalCountOutgoing)
                    .build();
        } catch (Exception e) {
            throw new SystemErrorException(e);
        }
    }

    @Override
    public Page<ResponsePkKur> getListPkKur(Pageable pageable, String query) {
        try {
            Page<Credit> creditPage = creditRepository.findAllAndFilter(pageable, query, CreditTypeEnum.PK_KUR);
            List<Credit> creditList = creditPage.getContent();
            List<ResponsePkKur> responsePkKurs = new ArrayList<>();
            for (Credit credit : creditList) {
                Optional<Account> createdByAccount = Optional.ofNullable(accountService.getCurrentAccount(credit.getCreatedBy()));
                ResponsePkKur response = ResponsePkKur.builder()
                        .number(credit.getNumber())
                        .name(credit.getName())
                        .requestNumber(credit.getRequestNumber())
                        .pkDate(credit.getPkDate())
                        .plafond(credit.getPlafond())
                        .businessType(credit.getBusinessType())
                        .guarantee(credit.getGuarantee())
                        .bindingType(credit.getBindingType())
                        .notary(credit.getNotary())
                        .id(credit.getId())
                        .note(credit.getNote())
                        .createdBy(accountService.getCurrentAccountId())
                        .type(credit.getType())
                        .createdDate(credit.getCreatedDate())
                        .build();
                createdByAccount.ifPresent(value -> response.setCreatedBy(value.getName()));
                responsePkKurs.add(response);

            }
            return new PageImpl<>(responsePkKurs, creditPage.getPageable(), creditPage.getTotalElements());
        } catch (Exception e) {
            throw new SystemErrorException(e);
        }
    }

    @Override
    public ResponseEnum createPkKur(ReqCreatePkKur req) {
        checkPhoneNumber(req.getNumber(), CreditTypeEnum.PK_KUR);

        try {
            Credit credit = Credit.builder()
                    .number(req.getNumber())
                    .name(req.getName())
                    .requestNumber(req.getRequestNumber())
                    .pkDate(req.getPkDate())
                    .plafond(req.getPlafond())
                    .businessType(req.getBusinessType())
                    .guarantee(req.getGuarantee())
                    .bindingType(req.getBindingType())
                    .notary(req.getNotary())
                    .note(req.getNote())
                    .createdBy(accountService.getCurrentAccountId())
                    .type(CreditTypeEnum.PK_KUR)
                    .build();
            creditRepository.save(credit);
            return ResponseEnum.SUCCESS;
        } catch (Exception e) {
            throw new SystemErrorException(e);
        }
    }

    private boolean checkPhoneNumber(String number, CreditTypeEnum type) {
        boolean checkNumber = creditRepository.existsAllByNumberAndType(number, type);
        if (checkNumber) {
            throw new BadRequestException(ResponseEnum.NUMBER_ALREADY_EXIST);
        }
        return false;
    }
}

