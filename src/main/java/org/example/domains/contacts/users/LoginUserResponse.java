package org.example.domains.contacts.users;

import lombok.Data;

@Data
public class LoginUserResponse {
    String token;
    String status;
    String error;
}
