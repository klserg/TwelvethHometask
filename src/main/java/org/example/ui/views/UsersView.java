package org.example.ui.views;

import org.example.domains.contacts.users.LoginUserRequest;
import org.example.domains.contacts.users.RegisterUserRequest;

public interface UsersView {
    LoginUserRequest readContactLogin();
    RegisterUserRequest readContactRegister();
}
