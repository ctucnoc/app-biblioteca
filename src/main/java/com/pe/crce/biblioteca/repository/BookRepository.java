package com.pe.crce.biblioteca.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pe.crce.biblioteca.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

	public Optional<Book> findByIdAndState(Long id, String state);
	
}
