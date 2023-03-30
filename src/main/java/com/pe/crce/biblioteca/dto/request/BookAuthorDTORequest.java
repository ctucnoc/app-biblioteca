package com.pe.crce.biblioteca.dto.request;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookAuthorDTORequest {

	@NotNull
	private Long idAuthor;
	
	@NotNull
	private Long idBook;
}
