package com.pe.crce.biblioteca.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "t_editorial", schema = "dbo")
public class Editorial {

	@Id
	@Column(name = "ideditorial")
	private Long id;
	
	@Column(name = "name",length = 100)
	private String name;
	
	@Column(name = "state", length = 1)
	private String state;
	
}
