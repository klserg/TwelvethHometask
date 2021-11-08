package org.example.services;

import org.example.domains.contacts.contacts.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ByteContactsService implements ContactsService{

    @Override
    public void add(Contact contact) {
        try (
                FileOutputStream fileOutputStream = new FileOutputStream("contacts.txt", true);
                ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
        ) {
            oos.writeObject(contact);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Contact> getAll() {
        List<Contact> list = new ArrayList<>();
        try(InputStream is = new FileInputStream("contacts.txt")) {
                ObjectInputStream ois = new ObjectInputStream(is);
                Contact contact = (Contact) ois.readObject();
                list.add(contact);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Contact> getByNamePart(String namePart) {
        return null;
    }

    @Override
    public List<Contact> getByValueStart(String start) {
        return null;
    }
}
