package org.example.ui.menu.contacts;

import lombok.RequiredArgsConstructor;
import org.example.services.ContactsService;
import org.example.ui.menu.MenuItem;
import org.example.ui.views.ContactsView;

@RequiredArgsConstructor
public class DeleteContactsMenuItem implements MenuItem {
    private final ContactsService contactsService;
    private final ContactsView contactsView;

    @Override
    public String getName() {
        return "Удалить контакт";
    }

    @Override
    public void run() {
        contactsService.delete(contactsView.readIdForRemove());
    }
}
