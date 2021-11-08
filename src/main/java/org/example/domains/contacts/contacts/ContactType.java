package org.example.domains.contacts.contacts;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ContactType {
    email("E-Mail"), phone("Телефон");
    private final String name;

    public int getId() {
        for (int i = 0; i < values().length ; i++) {
            if (values()[i] == this) return i;
        }
        return 0;
    }
}
