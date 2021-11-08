package org.example.ui.views;

import lombok.RequiredArgsConstructor;
import org.example.domains.contacts.users.LoginUserRequest;
import org.example.domains.contacts.users.RegisterUserRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

@RequiredArgsConstructor
public class ConsoleUserView implements UsersView{
    private final Scanner scanner;

    @Override
    public LoginUserRequest readContactLogin() {
        System.out.println("Введите логин:");
        String login = scanner.nextLine();
        System.out.println("Введите пароль:");
        String password = scanner.nextLine();
        return new LoginUserRequest()
                .setLogin(login)
                .setPassword(password);
    }

    @Override
    public RegisterUserRequest readContactRegister() {
        System.out.println("Введите логин:");
        String login = scanner.nextLine();
        System.out.println("Введите пароль:");
        String password = scanner.nextLine();
        System.out.println("Введите дату рождения:");
        String dateBorn = scanner.nextLine();
        SimpleDateFormat format = new SimpleDateFormat();
        Date docDate = null;
        format.applyPattern("yyyy-MM-dd");
        try {
            docDate= format.parse(dateBorn);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new RegisterUserRequest()
                .setLogin(login)
                .setPassword(password)
                .setDateBorn(docDate);
    }
}
