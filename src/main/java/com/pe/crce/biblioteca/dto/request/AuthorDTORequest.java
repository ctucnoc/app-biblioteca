package com.pe.crce.biblioteca.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTORequest{

	@NotNull
	@NotEmpty
	@NotBlank
	@Size(min = 5, max = 60)
	private String name;
	
	@NotNull
	@NotEmpty
	@NotBlank
	@Size(min = 5, max = 60)
	private String lastName;
}
