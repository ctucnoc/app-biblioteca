package com.pe.crce.biblioteca.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LibraryDTO implements Serializable{
	private Long id;
	private String name;
	private String description;
	private String address;
}
