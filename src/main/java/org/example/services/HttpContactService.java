package org.example.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.domains.contacts.contacts.Contact;
import org.example.domains.contacts.contacts.ContactsRequest;
import org.example.domains.contacts.contacts.ContactsResponse;
import org.example.domains.contacts.users.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@RequiredArgsConstructor
public class HttpContactService implements ContactsService, AuthService {
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private String token;
    public boolean isAuth = false;

    private <T> T makeGetRequest(String uri, Class<T> clazz) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(uri))
                .header("Accept", "application/json")
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(), clazz);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Ошибка запроса");
        }
    }

    private <T> T makeGetAuthRequest(String uri, Class<T> clazz) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(uri))
                .header("Authorization", String.format("Bearer %s", token))
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(), clazz);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Ошибка запроса");
        }
    }

    private <T> T makePostRequest(String uri, Object body, Class<T> clazz) {
        try {
            String bodyString = objectMapper.writeValueAsString(body);
            HttpRequest request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(bodyString))
                    .uri(URI.create(uri))
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(), clazz);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Ошибка запроса");
        }
    }

    private <T> T makePostAuthRequest(String uri, Object body, Class<T> clazz) {
        try {
            String bodyString = objectMapper.writeValueAsString(body);
            HttpRequest request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(bodyString))
                    .uri(URI.create(uri))
                    .header("Authorization", String.format("Bearer %s", token))
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(), clazz);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Ошибка запроса");
        }
    }

    public List<User> getUsers() {
        UsersResponse response = makeGetRequest(
                "https://mag-contacts-api.herokuapp.com/users",
                UsersResponse.class);
        if ("ok".equals(response.getStatus())) {
            return response.getUsers();
        } else {
            throw new RuntimeException("Ошибка запроса");
        }
    }

    public List<User> getAuthUsers() {
        UsersResponse response = makeGetAuthRequest(
                "https://mag-contacts-api.herokuapp.com/users2",
                UsersResponse.class);
        System.out.println(response.getStatus());
        if ("ok".equals(response.getStatus())) {
            return response.getUsers();
        } else {
            throw new RuntimeException("Ошибка запроса");
        }
    }

    @Override
    public boolean login (LoginUserRequest loginUserRequest) {
        LoginUserResponse response = makePostRequest(
                "https://mag-contacts-api.herokuapp.com/login",
                loginUserRequest,
                LoginUserResponse.class);
        token = response.getToken();
        isAuth = true;
        if (!"ok".equals(response.getStatus())) {
            isAuth=false;
            throw new RuntimeException(response.getError());
        }
        return isAuthorized();
    }

    @Override
    public boolean register (RegisterUserRequest registerUserRequest) {
        RegisterUserResponse response = makePostRequest(
                "https://mag-contacts-api.herokuapp.com/register",
                registerUserRequest,
                RegisterUserResponse.class);
        isAuth=false;
        if (!"ok".equals(response.getStatus())) {
            throw new RuntimeException(response.getError());
        }
        return isAuthorized();
    }

    @Override
    public boolean isAuthorized() {
        return isAuth;
    }

    @Override
    public void add(Contact contact) {
        ContactsResponse contactsResponse = makePostAuthRequest(
                "https://mag-contacts-api.herokuapp.com/contacts/add",
                contact,
                ContactsResponse.class);
        if (!"ok".equals(contactsResponse.getStatus())) {
            throw new RuntimeException("Error");
        }
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Contact> getAll() {
        ContactsResponse response = makeGetAuthRequest(
                "https://mag-contacts-api.herokuapp.com/contacts",
                ContactsResponse.class);
        if ("ok".equals(response.getStatus())) {
            return response.getContacts();
        } else {
            throw new RuntimeException("Error");
        }
    }

    @Override
    public List<Contact> getByNamePart(String namePart) {
        ContactsRequest request = new ContactsRequest()
                .setName(namePart);
        ContactsResponse contactsResponse = makePostAuthRequest(
                "https://mag-contacts-api.herokuapp.com/contacts/find",
                request,
                ContactsResponse.class);
        if ("ok".equals(contactsResponse.getStatus())) {
            return contactsResponse.getContacts();
        } else {
            throw new RuntimeException("Error");
        }
    }

    @Override
    public List<Contact> getByValueStart(String start) {
        ContactsRequest request = new ContactsRequest()
                .setValue(start);
        ContactsResponse contactsResponse = makePostAuthRequest(
                "https://mag-contacts-api.herokuapp.com/contacts/find",
                request,
                ContactsResponse.class);
        if ("ok".equals(contactsResponse.getStatus())) {
            return contactsResponse.getContacts();
        } else {
            throw new RuntimeException("Error");
        }
    }
}
