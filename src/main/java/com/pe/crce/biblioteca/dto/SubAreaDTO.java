package com.pe.crce.biblioteca.dto;

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
public class SubAreaDTO {
	private Long id;
	private String description;
	private AreaDTO area;
}
