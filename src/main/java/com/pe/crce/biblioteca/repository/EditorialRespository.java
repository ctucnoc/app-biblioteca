package com.pe.crce.biblioteca.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pe.crce.biblioteca.model.Editorial;

@Repository
public interface EditorialRespository extends JpaRepository<Editorial, Long>{
	
	public Page<Editorial> findByNameLikeAndState(String name,String state,Pageable pePageable);

}
