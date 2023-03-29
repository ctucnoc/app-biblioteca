package com.pe.crce.biblioteca.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.pe.crce.biblioteca.constant.BibliotecaConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = BibliotecaConstant.TAB_NAME_SPECIMEN, schema = BibliotecaConstant.SEC_NAME_DBO)
public class Specimen {
	@Id
	@Column(name = "idspecimen")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	@Column(name = "code")
	private String code;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "dateregister")
	private Date dateRegister;
	
	@Column(name = "state", insertable = false)
	private String state;
	
	@ManyToOne
	@JoinColumn(name = "idbook")
	private Book book;
	
	@ManyToOne
	@JoinColumn(name = "idzone")
	private Zone zone;
		
	@ManyToOne
	@JoinColumn(name = "idspecimenstate")
	private SpecimenState specimenState;
}
