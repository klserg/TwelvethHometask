package org.example.ui.menu.contacts;

import lombok.RequiredArgsConstructor;
import org.example.domains.contacts.contacts.Contact;
import org.example.services.ContactsService;
import org.example.ui.menu.MenuItem;
import org.example.ui.views.ContactsView;

@RequiredArgsConstructor
public class AddContactsMenuItem implements MenuItem {
    private final ContactsService contactsService;
    private final ContactsView contactsView;


    @Override
    public String getName() {
        return "Добавить контакт";
    }

    @Override
    public void run() {
        Contact contact = contactsView.readContact();
        contactsService.add(contact);
    }
}
