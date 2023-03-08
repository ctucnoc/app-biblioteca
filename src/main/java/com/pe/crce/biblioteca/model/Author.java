package com.pe.crce.biblioteca.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.pe.crce.biblioteca.constant.BibliotecaConstant;
import lombok.Data;

@Data
@Entity
@Table(name = BibliotecaConstant.TAB_NAME_AUTHOR, schema = BibliotecaConstant.SEC_NAME_DBO)
public class Author {
	
	@Id
	@Column(name = "idauthor")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "lastname")
	private String lastName;

	@Column(name = "state")
	private String state;
}
