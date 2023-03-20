package com.pe.crce.biblioteca.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EditorialDTORequest{
	
	@NotNull
	@NotBlank
	@NotEmpty
	private String name;
}
