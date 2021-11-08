package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.domains.contacts.contacts.Contact;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public abstract class AbstractFileContactsService implements ContactsService{

    protected final Path file;
    protected abstract List<Contact> load();
    protected abstract void save(List<Contact> contacts);
    protected abstract void update(List<Contact> contacts);

    private long getNextId(List<Contact> contacts) {
        return contacts.stream()
                .map(Contact::getId)
                .max(Long::compare)
                .orElse(0L) + 1;
    }

    @Override
    public void add(Contact contact) {
        List<Contact> contacts = load();
        contact.setId(getNextId(contacts));
        contacts.add(contact);
        save(contacts);
    }

    @Override
    public void delete(Long id) {
        List<Contact> contacts = load()
                .stream()
                .filter(c -> !c.getId().equals(id))
                .collect(Collectors.toList());
        update(contacts);
    }

    @Override
    public List<Contact> getAll() {
        return load();
    }

    @Override
    public List<Contact> getByNamePart(String namePart) {
        return load().stream()
                .filter(c-> c.getName().contains(namePart))
                .collect(Collectors.toList());
    }

    @Override
    public List<Contact> getByValueStart(String start) {
        return load().stream()
                .filter(c-> c.getValue().startsWith(start))
                .collect(Collectors.toList());
    }

}
