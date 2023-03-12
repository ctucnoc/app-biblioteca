package com.pe.crce.biblioteca.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class AuthorDTO implements Serializable{

	private Long id;
	private String authorName;
}
