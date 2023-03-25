package com.pe.crce.biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
	private Long id;
	private String title;
	private String subtitle;
	private String isbn;
	private String description;
	private String numberPage;
	private String yearPublication;
	private EditorialDTO editorial;
	private SubAreaDTO subArea;
}
