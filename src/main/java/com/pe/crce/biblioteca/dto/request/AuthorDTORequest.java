package com.pe.crce.biblioteca.dto.request;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTORequest implements Serializable {

	private String name;
	private String lastName;
}
