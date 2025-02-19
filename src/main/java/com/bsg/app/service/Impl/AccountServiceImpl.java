package com.bsg.app.service.Impl;

import com.bsg.app.entities.Account;
import com.bsg.app.enums.ResponseEnum;
import com.bsg.app.exception.BadRequestException;
import com.bsg.app.exception.NotAuthorizedException;
import com.bsg.app.exception.NotFoundException;
import com.bsg.app.exception.SystemErrorException;
import com.bsg.app.model.request.RequestChangePassword;
import com.bsg.app.model.request.RequestCreateAccount;
import com.bsg.app.model.response.ResponseCreateNewAccount;
import com.bsg.app.model.response.ResponseGetMe;
import com.bsg.app.model.response.ResponseListAccount;
import com.bsg.app.repository.AccountRepository;
import com.bsg.app.service.AccountService;
import com.bsg.app.utilities.AuthConstant;
import com.bsg.app.utilities.StringUtilities;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final HttpServletRequest httpServletRequest;

    @Override
    public ResponseCreateNewAccount createNewAccount(RequestCreateAccount req) {
        boolean checkExistAccount = accountRepository.existsAccountByUsername(req.getUsername());
        if (checkExistAccount) {
            throw new BadRequestException(ResponseEnum.ACCOUNT_ALREADY_EXIST);
        }
        String generatePassword = StringUtilities.generateRandomString();
        String password = passwordEncoder.encode(generatePassword);
        String avatar = createAvatar(req.getName());
        Account account = Account.builder().name(req.getName()).username(req.getUsername()).password(password).build();
        try {

            accountRepository.save(account);
            return ResponseCreateNewAccount.builder().name(account.getName()).password(generatePassword).username(account.getUsername()).name(account.getName()).build();
        } catch (Exception e) {
            throw new SystemErrorException(e);
        }
    }


    @Override
    public String createAvatar(String name) {
        return "https://ui-avatars.com/api/?name=" + name + "format=png";
    }

    public Account getCurrentAccount() {
        String currentUserId = httpServletRequest.getAttribute(AuthConstant.HEADER_X_WHO).toString();
        Optional<Account> account = accountRepository.findById(currentUserId);
        return account.orElse(null);
    }

    @Override
    public Account getCurrentAccount(String id) {
        if (id != null) {
            Optional<Account> account = accountRepository.findById(id);

            if (account.isEmpty()) {
                throw new NotFoundException(ResponseEnum.ACCOUNT_NOT_FOUND.name());
            }
            return account.get();
        } else {
            return null;
        }

    }

    @Override
    public String getCurrentAccountId() {
        return httpServletRequest.getAttribute(AuthConstant.HEADER_X_WHO) != null ? httpServletRequest.getAttribute(AuthConstant.HEADER_X_WHO).toString() : null;

    }

    @Override
    public Page<ResponseListAccount> getListAccount(Pageable pageable, String query) {
        try {
            Page<Account> accountPage = accountRepository.findAllAndFilter(query, pageable);
            List<Account> accountList = accountPage.getContent();
            List<ResponseListAccount> responseListAccounts = new ArrayList<>();
            for (Account account : accountList) {

                ResponseListAccount responseListAccount = ResponseListAccount.builder().name(account.getName()).username(account.getUsername()).id(account.getId()).createdDate(account.getCreatedDate()).build();
                responseListAccounts.add(responseListAccount);
            }
            return new PageImpl<>(responseListAccounts, accountPage.getPageable(), accountPage.getTotalElements());

        } catch (Exception e) {
            throw new SystemErrorException(e);
        }
    }

    @Override
    public ResponseCreateNewAccount resetPassword(String id) {
        Optional<Account> findAccount = accountRepository.findById(id);
        if (findAccount.isEmpty()) {
            throw new NotFoundException(ResponseEnum.ACCOUNT_NOT_FOUND.name());
        }
        Account account = findAccount.get();
        String password = StringUtilities.generateRandomString();
        String newPassword = passwordEncoder.encode(password);
        account.setPassword(newPassword);
        try {
            accountRepository.save(account);
            return ResponseCreateNewAccount.builder().name(account.getName()).password(password).username(account.getUsername()).name(account.getName()).build();
        } catch (Exception e) {
            throw new SystemErrorException(e);
        }
    }

    @Override
    public ResponseEnum changePassword(RequestChangePassword req) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        Optional<Account> optionalAccount = accountRepository.findByUsername(currentUsername);
        if (optionalAccount.isEmpty()) {
            throw new BadRequestException(ResponseEnum.ACCOUNT_NOT_FOUND);
        }

        Account account = optionalAccount.get();

        if (!passwordEncoder.matches(req.getOldPassword(), account.getPassword())) {
            throw new BadRequestException(ResponseEnum.INVALID_OLD_PASSWORD);
        }

        String encodedNewPassword = passwordEncoder.encode(req.getNewPassword());
        account.setPassword(encodedNewPassword);

        try {

            accountRepository.save(account);

            return ResponseEnum.PASSWORD_CHANGED_SUCCESSFULLY;
        } catch (Exception e) {
            throw new SystemErrorException(e);
        }
    }

    @Transactional
    @Override
    public ResponseEnum deleteAccount(String id) {
        try {
            String currentUserId = getCurrentAccountId();
            Optional<Account> optionalAccount = accountRepository.findById(id);
            if (optionalAccount.isEmpty()) {
                throw new BadRequestException(ResponseEnum.ACCOUNT_NOT_FOUND);
            }
            accountRepository.delete(optionalAccount.get());
            return ResponseEnum.ACCOUNT_DELETED_SUCCESSFULLY;
        } catch (Exception e) {
            throw new SystemErrorException(e);
        }
    }

    @Override
    public ResponseGetMe getMe() {
        Optional<Account> getAccount = Optional.ofNullable(getCurrentAccount());
        if (getAccount.isEmpty()) {
            throw new NotAuthorizedException(ResponseEnum.UNAUTHORIZED.name());
        }
        Account account = getAccount.get();
        try {
            return ResponseGetMe.builder()
                    .id(account.getId())
                    .name(account.getName())
                    .username(account.getUsername())
                    .createdDate(account.getCreatedDate())
                    .build();
        } catch (Exception e) {
            throw new SystemErrorException(e);
        }
    }

}

