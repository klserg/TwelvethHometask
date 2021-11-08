package org.example.ui.menu.users;

import lombok.RequiredArgsConstructor;
import org.example.services.HttpContactService;
import org.example.ui.menu.MenuItem;
import org.example.ui.views.UsersView;

@RequiredArgsConstructor
public class LoginUserMenuItem implements MenuItem {
    private final HttpContactService contactsService;
    private final UsersView usersView;

    @Override
    public String getName() {
        return "Залогиниться";
    }

    @Override
    public void run() {
        contactsService.login(usersView.readContactLogin());
    }
}
