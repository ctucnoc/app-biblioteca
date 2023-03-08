package com.pe.crce.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.pe.crce.biblioteca.model.Author;
import com.pe.crce.biblioteca.constant.BibliotecaConstant;;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

	@Query(value = BibliotecaConstant.SP_SEARCH_AUTHOR, nativeQuery = true, countQuery = BibliotecaConstant.SP_SEARCH_AUTHOR_COUNT)
	public List<Author> findByKeyWordSQL(String key_word, String state);
	
}
