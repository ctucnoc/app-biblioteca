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
import lombok.ToString;

@Data
@Builder
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = BibliotecaConstant.TAB_NAME_BOOK_AUTHOR, schema = BibliotecaConstant.SEC_NAME_DBO)
public class BookAuthor {

	@Id
	@Column(name = "idbookauthor")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	@Column(name = "state", insertable = false)
	private String state;
	
	@ManyToOne
	@JoinColumn(name = "idauthor")
	private Author author;
	
	@ManyToOne
	@JoinColumn(name = "idbook")
	private Book book;
}
