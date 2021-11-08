package org.example.domains.contacts.contacts;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@RequiredArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "contact")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Contact implements Serializable {
    private Long id;
    @JacksonXmlProperty(localName = "userName")
    private String name;
    @JacksonXmlProperty(localName = "contactType")
    @JsonIgnoreProperties("type")
    private ContactType type;
    private String value;
}
