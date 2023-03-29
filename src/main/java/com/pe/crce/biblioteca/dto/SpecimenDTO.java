package com.pe.crce.biblioteca.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecimenDTO {
	private Long id;
	private String code;
	private Integer quantity;
	private Date dateRegister;
	private BookDTO book;
	private ZoneDTO zone;
	private SpecimenStateDTO specimenState;
}
