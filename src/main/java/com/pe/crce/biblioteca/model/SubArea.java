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
@ToString
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = BibliotecaConstant.TAB_NAME_SUB_AREA, schema = BibliotecaConstant.SEC_NAME_DBO)
public class SubArea {

	@Id
	@Column(name = "idsubarea")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "description")
	private String description;

	@Column(name = "state", insertable = false)
	private String state;

	@ManyToOne
	@JoinColumn(name = "idarea")
	private Area area;
}
