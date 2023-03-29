package com.pe.crce.biblioteca.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecimenDTORequest {
	
	@NotNull
	@NotEmpty
	@NotBlank
	private String code;
	
	@NotNull
	private Integer quantity;
		
	@NotNull
	private Long idBook;
	
	@NotNull
	private Long idZone;
	
	@NotNull
	private Long idSpecimenState;
}
