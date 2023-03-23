package com.pe.crce.biblioteca.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.pe.crce.biblioteca.model.Author;
import com.pe.crce.biblioteca.constant.BibliotecaConstant;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

	@Query(value = BibliotecaConstant.SP_SEARCH_AUTHOR, nativeQuery = true, countQuery = BibliotecaConstant.SP_SEARCH_AUTHOR_COUNT)
	public Page<Author> findByKeyWordSQL(String key_word, String state,Pageable pageable);
	
	@Query(value = BibliotecaConstant.SP_SAVE_AUTHOR, nativeQuery = true, countQuery = BibliotecaConstant.SP_SAVE_AUTHOR_COUNT)
	public Author saveSQL(String name, String lastName);
	
	public Optional<Author> findByIdAndState(Long id, String state);
	
}
