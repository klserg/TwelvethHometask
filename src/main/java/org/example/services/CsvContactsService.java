package org.example.services;

import org.example.domains.contacts.contacts.Contact;
import org.example.domains.contacts.contacts.ContactType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CsvContactsService extends AbstractFileContactsService{
    public CsvContactsService(Path file) {
        super(file);
    }
    @Override
    protected List<Contact> load() {
        if (!Files.exists(file)) return new ArrayList<>();
            try {
                return Files.lines(file)
                        .map(s -> s.split(","))
                        .filter(a -> a.length == 4)
                        //.peek(a-> System.out.println(Arrays.toString(a)))
                        .map(arr -> new Contact()
                                .setId(Long.parseLong(arr[0]))
                                .setType(ContactType.values()[Integer.parseInt(arr[1])])
                                .setName(arr[2])
                                .setValue(arr[3]))
                        .collect(Collectors.toList());
            } catch (IOException e) {
                throw new RuntimeException("FAIL READ FILE!!!");
            }
        }

        private String toCsvString (Contact contact){
            StringBuilder builder = new StringBuilder();
            int typeId = contact.getType().getId();
            builder.append(contact.getId()).append(',')
                    .append(typeId).append(",")
                    .append(contact.getName()).append(",")
                    .append(contact.getValue());
            return builder.toString();
        }

        @Override
        protected void save (List <Contact> contacts) {
            try {
                Files.write(file,
                        contacts.stream().map(this::toCsvString).collect(Collectors.toList()),
                        StandardOpenOption.CREATE, StandardOpenOption.WRITE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    protected void update (List <Contact> contacts) {
        try {
            if (Files.deleteIfExists(file))
            Files.write(file,
                    contacts.stream().map(this::toCsvString).collect(Collectors.toList()),
                    StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
