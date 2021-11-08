package org.example.ui.views;

import lombok.RequiredArgsConstructor;
import org.example.domains.contacts.contacts.Contact;
import org.example.domains.contacts.contacts.ContactType;

import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
public class ConsoleContactsView implements ContactsView {
    private final Scanner scanner;

    @Override
    public void show(List<Contact> contactList) {
        for (Contact c: contactList) {
            System.out.printf("%d - %s[%s]: %s\n",
                    c.getId(), c.getName(), c.getType().getName(), c.getValue());
        }
    }

    @Override
    public Contact readContact() {
        System.out.println("Введите имя:");
        String name = scanner.nextLine();
        System.out.println("Выберите тип:");
        ContactType type = null;
        while (type==null) {
            ContactType[] types = ContactType.values();
            for (int i = 0; i < types.length; i++) {
                System.out.printf("\t%d - %s\n", i + 1, types[i].getName());
            }
            System.out.print("-> ");
            int ch = scanner.nextInt() - 1;
            scanner.nextLine();
            if (ch<0 || ch>types.length) {
                System.out.println("Try again");
                continue;
            }
            type = types[ch];
        }
        System.out.println("Введите контакт:");
        String value = scanner.nextLine();

        return new Contact()
                .setName(name)
                .setType(type)
                .setValue(value);
    }

    @Override
    public Long readIdForRemove() {
        System.out.println("Введите ID контакта:");
        Long value = scanner.nextLong();
        return value;
    }

    @Override
    public String readDataForSearch() {
        System.out.println("Введите имя, телефон или email контакта:");
        String value = scanner.nextLine();
        return value;
    }


}
