package com.pe.crce.biblioteca.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDTORequest {
	
	@NotNull
	@NotEmpty
	private String title;
	
	@NotNull
	@NotEmpty
	private String subTitle;
	private String isbn;
	private String description;
	private String numberPage;
	private String yearPublication;
	
	@NotNull
	private Long idEditorial;
	
	@NotNull
	private Long idSubArea;
}
