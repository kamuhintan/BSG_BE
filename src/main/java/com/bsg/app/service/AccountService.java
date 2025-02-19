package com.bsg.app.service;

import com.bsg.app.entities.Account;
import com.bsg.app.enums.ResponseEnum;
import com.bsg.app.model.request.RequestChangePassword;
import com.bsg.app.model.request.RequestCreateAccount;
import com.bsg.app.model.response.ResponseCreateNewAccount;
import com.bsg.app.model.response.ResponseGetMe;
import com.bsg.app.model.response.ResponseListAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountService {

    ResponseCreateNewAccount createNewAccount(RequestCreateAccount req);

    String createAvatar(String name);

    Account getCurrentAccount();

    Account getCurrentAccount(String id);

    String getCurrentAccountId();

    Page<ResponseListAccount> getListAccount(Pageable pageable, String query);

    ResponseCreateNewAccount resetPassword(String id);

    ResponseEnum changePassword(RequestChangePassword req);

    ResponseEnum deleteAccount(String id);

    ResponseGetMe getMe();
}
