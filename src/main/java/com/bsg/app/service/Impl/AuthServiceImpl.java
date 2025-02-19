package com.bsg.app.service.Impl;

import com.bsg.app.entities.Account;
import com.bsg.app.enums.ResponseEnum;
import com.bsg.app.exception.BadRequestException;
import com.bsg.app.exception.SystemErrorException;
import com.bsg.app.model.request.ReqRegister;
import com.bsg.app.model.request.RequestSignIn;
import com.bsg.app.model.response.ResponseSignIn;
import com.bsg.app.repository.AccountRepository;
import com.bsg.app.service.AccountService;
import com.bsg.app.service.AuthService;
import com.bsg.app.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AccountService accountService;

    @Override
    public ResponseEnum registerSuperAdmin(ReqRegister reqRegister) {
        boolean existAccount = accountRepository.existsAccountByUsername(reqRegister.getUsername());
        if (existAccount) {
            throw new BadRequestException(ResponseEnum.ACCOUNT_ALREADY_EXIST);
        }
        String password = passwordEncoder.encode(reqRegister.getPassword());

        try {
            Account account = Account.builder()
                    .name(reqRegister.getName())
                    .username(reqRegister.getUsername())
                    .password(password)
                    .avatar(accountService.createAvatar(reqRegister.getName()))
                    .build();
            accountRepository.save(account);
            return ResponseEnum.SUCCESS;
        } catch (Exception e) {
            throw new SystemErrorException(e);
        }
    }

    @Override
    public ResponseSignIn signIn(RequestSignIn req) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwt = jwtService.generateToken(userDetails);

            return ResponseSignIn.builder().token(jwt).build();

        } catch (BadCredentialsException e) {
            throw new BadRequestException(ResponseEnum.SIGN_IN_FAILED);
        } catch (Exception e) {
            throw new SystemErrorException(e);
        }
    }


}

