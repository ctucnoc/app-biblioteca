package com.pe.crce.biblioteca.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pe.crce.biblioteca.model.Specimen;

@Repository
public interface SpecimenRepository extends JpaRepository<Specimen, Long>{

	@Query("SELECT es FROM Specimen es \n"+
	"INNER JOIN es.book eb \n"+
	"INNER JOIN es.zone ez \n"+
	"INNER JOIN ez.library el \n"+
	"WHERE es.state = ?2 and \n"+
	"LOWER(CONCAT(es.code, eb.title, eb.subtitle, eb.isbn, eb.description, ez.description, el.description))"+
	"LIKE CONCAT('%',?1,'%')")
	public Page<Specimen> findByKeyWordAndStateJPQL(String key_word, String state, Pageable pageable);
	
	public Optional<Specimen> findByIdAndState(Long id, String state);
}
