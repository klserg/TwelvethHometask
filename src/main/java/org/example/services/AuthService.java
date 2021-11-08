package org.example.services;

import org.example.domains.contacts.users.LoginUserRequest;
import org.example.domains.contacts.users.RegisterUserRequest;

public interface AuthService {
    boolean login(LoginUserRequest loginUserRequest);
    boolean register(RegisterUserRequest registerUserRequest);
    boolean isAuthorized();
}
