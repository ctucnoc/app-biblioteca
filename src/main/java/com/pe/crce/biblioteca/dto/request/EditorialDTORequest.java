package com.pe.crce.biblioteca.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class EditorialDTORequest {

	// LOS ATRIBUTOS NO SEAN NULOS.
	@NotNull

	// LOS ATRIBUTOS NO SEAN BACIOS.
	@NotEmpty

	// LOS ATRIBUTOS NOS SEAN NULAS NI BACIOS.
	@NotBlank
	
	//VALIDA LA LONGITUD MINIMA Y MAXIMA DE UNA CADENA
	@Size(min = 5,max = 100)
	private String name;
}
