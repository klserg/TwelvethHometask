package org.example.ui.menu.contacts;

import lombok.RequiredArgsConstructor;
import org.example.domains.contacts.contacts.Contact;
import org.example.services.ContactsService;
import org.example.ui.menu.MenuItem;
import org.example.ui.views.ContactsView;

import java.util.List;

@RequiredArgsConstructor
public class FindContactsByValueMenuItem implements MenuItem {
    private final ContactsService contactsService;
    private final ContactsView contactsView;

    @Override
    public String getName() {
        return "Найти контакт по телефону или еmail";
    }

    @Override
    public void run() {
        List<Contact> contacts = contactsService.getByValueStart(contactsView.readDataForSearch());
        contactsView.show(contacts);
    }
}
