package com.pe.crce.biblioteca.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.pe.crce.biblioteca.model.Library;

@Repository
public interface LibraryRepositorio extends JpaRepository<Library, Long> {

	@Query("select li from Library li where LOWER(li.name) like CONCAT('%',LOWER(?1),'%') and li.state = ?2")
	public Page<Library> findByNameLikeJPA(String key_word, String state, Pageable pageable);

	// =============================================================================================
	// PRIMERA FORMA .- POR NOMBRE DE METODO
	// =============================================================================================
	public List<Library> findByDescriptionContainingOrderByIdAsc(String description);

	// =============================================================================================
	// SEGUNDA FORMA .- MEDIANTE JPQL
	// =============================================================================================
	@Query("select l from Library l where id =?1")
	public Library findByIdJPSQL(Long id);

	// =============================================================================================
	// TERCERA FORMA .- MEDIANTE INSTRUCCION SQL
	// =============================================================================================
	@Query(value = "select count(*)>0 from dbo.t_library where idlibrary = ?1", nativeQuery = true)
	public Boolean existsByidSQL(Long id);
	
	public Optional<Library> findByIdAndState(Long id, String state);
	
	@Query("select li from Library li where LOWER(CONCAT(li.name,li.description,li.address)) like CONCAT('%',LOWER(?1),'%') and li.state = ?2")
	public List<Library> findByKeyWordLikeJPA(String key_word, String state);

}
