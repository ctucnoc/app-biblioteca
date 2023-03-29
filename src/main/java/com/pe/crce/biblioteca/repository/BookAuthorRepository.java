package com.pe.crce.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pe.crce.biblioteca.model.BookAuthor;

@Repository
public interface BookAuthorRepository extends JpaRepository<BookAuthor, Long>{

}
