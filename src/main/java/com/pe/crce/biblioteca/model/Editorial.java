package com.pe.crce.biblioteca.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.pe.crce.biblioteca.constant.BibliotecaConstant;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = BibliotecaConstant.TAB_NAME_EDITORIAL,schema = BibliotecaConstant.SEC_NAME_DBO)
public class Editorial {

	@Id
	@Column(name = "ideditorial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name",length = 100)
	private String name;
	
	@Column(name = "state", length = 1)
	private String state;
	
}
