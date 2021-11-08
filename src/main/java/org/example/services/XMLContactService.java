package org.example.services;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.example.domains.contacts.contacts.Contact;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class XMLContactService implements ContactsService {

    final File file;
    List<Contact> contacts = new ArrayList<>();

    public XMLContactService(File file) {
        this.file = file;
        contacts = load();
    }

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
        contacts = load()
                .stream()
                .filter(c -> !c.getId().equals(id))
                .collect(Collectors.toList());
        save(contacts);
    }

    @Override
    public List<Contact> getAll() {
        return contacts;
    }

    @Override
    public List<Contact> getByNamePart(String namePart) {
        return contacts.stream()
                .filter(c-> c.getName().contains(namePart))
                .collect(Collectors.toList());
    }

    @Override
    public List<Contact> getByValueStart(String start) {
        return contacts.stream()
                .filter(c-> c.getValue().startsWith(start))
                .collect(Collectors.toList());
    }

    private List<Contact> load() {
        if (!file.exists()) save(List.of());
        XmlMapper xmlMapper = new XmlMapper();
        try (BufferedReader bufferedReader= new BufferedReader(new FileReader(file))) {
           for (Contact person : contacts)
                xmlMapper.readValue(file, Contact.class);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return contacts;
    }

    private void save (List <Contact> contacts) {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION,true);
        try (FileWriter fileWriter = new FileWriter(file, StandardCharsets.UTF_8)) {
            xmlMapper.writerWithDefaultPrettyPrinter().writeValue(fileWriter, contacts);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
