package com.foxminded.aprihodko.carrestservice.controller.security;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foxminded.aprihodko.carrestservice.security.dto.Message;

@RestController
@RequestMapping(path = "api", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class AuthController {

	@GetMapping(value = "/public")
	public Message publicEndpoint() {
		return new Message("All good. You DO NOT need to be authenticated to call /api/public.");
	}

	@GetMapping(value = "/private")
	public Message privateEndpoint() {
		return new Message("All good. You can see this because you are Authenticated.");
	}

	@GetMapping(value = "/private-scoped")
	public Message privateScopedEndpoint() {
		return new Message(
				"All good. You can see this because you are Authenticated with a Token granted the 'read:messages' scope");
	}
}
