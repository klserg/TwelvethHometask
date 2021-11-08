package org.example.domains.contacts.contacts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactsResponse {
    @JsonProperty("contacts")
    private List<Contact> contacts;

    @JsonProperty("status")
    private String status;
}
