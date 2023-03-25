package com.pe.crce.biblioteca.model;

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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = BibliotecaConstant.TAB_NAME_BOOK, schema = BibliotecaConstant.SEC_NAME_DBO)
public class Book {
	@Id
	@Column(name = "idbook")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "subtitle")
	private String subtitle;
	
	@Column(name = "isbn")
	private String isbn;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "numberpage")
	private String numberPage;
	
	@Column(name = "yearpublication")
	private String yearPublication;
	
	@Column(name = "state",insertable = false)
	private String state;
	
	@ManyToOne
	@JoinColumn(name = "ideditorial")
	private Editorial editorial;
	
	@ManyToOne
	@JoinColumn(name = "idsubarea")
	private SubArea subArea;
}
