package org.example.services;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InternalAuthService {
    private final String pass;
    private final String login;
    private boolean isAuth = false;


    public boolean login(String login, String password) {
        return isAuth = this.login.equals(login) && this.pass.equals(password);
    }


    public boolean register(String login, String password, String birthDay) {
        return false;
    }


    public boolean isAuthorized() {
        return isAuth;
    }
}
