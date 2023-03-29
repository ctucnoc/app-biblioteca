package com.pe.crce.biblioteca.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.pe.crce.biblioteca.constant.BibliotecaConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = BibliotecaConstant.TAB_NAME_SPECIMEN_STATE, schema = BibliotecaConstant.SEC_NAME_DBO)
public class SpecimenState {
	@Id
	@Column(name = "idspecimenstate")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	@Column(name = "description")
	private String description;
	
	@Column(name = "state", insertable = false)
	private String state;
}
