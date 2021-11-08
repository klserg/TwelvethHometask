package org.example.domains.contacts.users;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UsersResponse {

	@JsonProperty("users")
	private List<User> users;

	@JsonProperty("status")
	private String status;
}