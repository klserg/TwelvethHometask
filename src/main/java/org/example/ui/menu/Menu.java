package org.example.ui.menu;

import lombok.RequiredArgsConstructor;
import org.example.services.HttpContactService;

import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
public class Menu {
    private final Scanner scanner;
    private final List<MenuItem> items;

    public void run(HttpContactService contactService) {
        while (true) {
            showMenu();
            int choice = getChoice();
            if (choice >= 0 && choice < items.size()) {
                items.get(choice).run();
                if (items.get(choice).exit() || contactService.isAuthorized()) {
                    contactService.isAuth = false;
                    break;
                }
            } else {
                System.out.println("Incorrect Input");
            }
        }
    }

    private int getChoice() {
        System.out.println("Enter your choice:");
        int ch =  scanner.nextInt()-1;
        scanner.nextLine();
        return ch;
    }

    private void showMenu() {
        for (int i = 0; i < items.size(); i++) {
            System.out.printf("%d - %s\n", i + 1, items.get(i).getName());
        }
    }
}
