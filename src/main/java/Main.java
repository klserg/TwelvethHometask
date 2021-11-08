import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.services.*;
import org.example.ui.menu.ExitMenuItem;
import org.example.ui.menu.Menu;

import org.example.ui.menu.contacts.*;
import org.example.ui.menu.users.LoginUserMenuItem;
import org.example.ui.menu.users.RegisterUserMenuItem;
import org.example.ui.views.ConsoleContactsView;
import org.example.ui.views.ConsoleUserView;
import org.example.ui.views.ContactsView;
import org.example.ui.views.UsersView;

import java.io.*;
import java.net.http.HttpClient;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HttpClient client = HttpClient.newBuilder().build();
        ObjectMapper objectMapper = new ObjectMapper();

        // ContactsService contactsService = new CsvContactsService(Path.of("1.csv"));
        // ContactsService contactsService = new ByteContactsService();
        //ContactsService contactsService = new InMemoryContactsService();
        // ContactsService contactsService = new XMLContactService(new File("list.xml"));
        // ContactsService contactsService = new JSONContactService(new File("list.json"));
        ContactsService contactsService = new HttpContactService(client, objectMapper);
        ContactsView contactsView = new ConsoleContactsView(scanner);
        UsersView usersView = new ConsoleUserView(scanner);

        Menu authMenu = new Menu(scanner, List.of(
                new LoginUserMenuItem((HttpContactService) contactsService, usersView), // Use account login=user, pass=1
                new RegisterUserMenuItem((HttpContactService) contactsService, usersView),
                new ExitMenuItem("Выход")));

        authMenu.run((HttpContactService) contactsService);

            Menu menu = new Menu(scanner, List.of(
                    new ShowAllMenuItem(contactsService, contactsView),
                    new AddContactsMenuItem(contactsService, contactsView),
                    new DeleteContactsMenuItem(contactsService, contactsView),
                    new FindContactsByNameMenuItem(contactsService, contactsView),
                    new FindContactsByValueMenuItem(contactsService, contactsView),
                    new ExitMenuItem("Выход")));
            menu.run((HttpContactService) contactsService);
        }


//        File file = new File("./");
//        searchJavaFile(file);

    public static void searchJavaFile(File file) {
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                if (f.getName().contains(".java")) {
                    System.out.println(f.getName());
                }
                    searchJavaFile(f);
                }
            }
        }
    }

