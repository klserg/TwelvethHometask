package org.example.services;

import org.example.domains.contacts.contacts.Contact;

import java.util.List;

public interface ContactsService {
    void add(Contact contact);
    void delete(Long id);
    List<Contact> getAll();
    List<Contact> getByNamePart(String namePart);
    List<Contact> getByValueStart(String start);
}
