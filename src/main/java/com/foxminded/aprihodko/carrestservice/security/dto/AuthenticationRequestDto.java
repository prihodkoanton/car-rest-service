package com.foxminded.aprihodko.carrestservice.security.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class AuthenticationRequestDto implements Serializable {

	private String username;
	private String password;
}
