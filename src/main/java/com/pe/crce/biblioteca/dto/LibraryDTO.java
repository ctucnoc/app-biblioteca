package com.pe.crce.biblioteca.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class LibraryDTO implements Serializable{
	private Long id;
	private String name;
	private String description;
	private String address;
}
