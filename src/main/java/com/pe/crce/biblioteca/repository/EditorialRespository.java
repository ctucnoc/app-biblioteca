package com.pe.crce.biblioteca.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.pe.crce.biblioteca.model.Editorial;

@Repository
public interface EditorialRespository extends JpaRepository<Editorial, Long> {

	public Page<Editorial> findByNameLikeAndState(String name, String state, Pageable pePageable);

	// =============================================================================================
	// PRIMERA FORMA .- POR NOMBRE DE METODO
	// =============================================================================================
	public List<Editorial> findByNameContainingOrderByIdAsc(String name);

	// =============================================================================================
	// SEGUNDA FORMA .- MEDIANTE JPQL
	// =============================================================================================
	@Query("select e from Editorial e where id =?1")
	public Editorial findByIdJPQL(Long id);

	// =============================================================================================
	// TERCERA FORMA .- MEDIANTE INSTRUCCION SQL
	// =============================================================================================
	@Query(value = "select count(*)>0 from dbo.t_editorial where ideditorial =?1", nativeQuery = true)
	public Boolean existByIdSQL(Long id);
	
	public Optional<Editorial> findByIdAndState(Long id, String state);
}
