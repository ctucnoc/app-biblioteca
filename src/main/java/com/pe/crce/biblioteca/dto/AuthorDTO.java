package com.pe.crce.biblioteca.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthorDTO implements Serializable{

	private Long id;
	private String authorName;
}
