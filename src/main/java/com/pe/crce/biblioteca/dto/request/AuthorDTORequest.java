package com.pe.crce.biblioteca.dto.request;

import java.io.Serializable;
import lombok.Data;

@Data
public class AuthorDTORequest implements Serializable {

	private String name;
	private String lastName;
}
