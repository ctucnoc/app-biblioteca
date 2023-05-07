package com.pe.crce.biblioteca.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubAreaDTORequest {

	@NotNull
	@NotEmpty
	@Size(min = 5, max = 100)
	private String description;
	
	@NotNull
	private Long idArea;
}
