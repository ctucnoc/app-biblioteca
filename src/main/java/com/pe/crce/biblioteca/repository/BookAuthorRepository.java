package com.pe.crce.biblioteca.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pe.crce.biblioteca.model.Book;
import com.pe.crce.biblioteca.model.BookAuthor;

@Repository
public interface BookAuthorRepository extends JpaRepository<BookAuthor, Long>{

	public Page<BookAuthor> findByBookAndState(Book book, String state, Pageable pageable);
	public Optional<BookAuthor> findByIdAndState(Long id, String state);
}
