package com.pe.crce.biblioteca.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pe.crce.biblioteca.model.Library;
import com.pe.crce.biblioteca.model.Zone;

@Repository
public interface ZonaRepository extends JpaRepository<Zone, Long>{

	public Page<Zone> findByDescriptionContainingAndState(String description, String state,Pageable pageable);
	
	public Optional<Zone> findByIdAndState(Long id, String state);
	
	public Page<Zone> findByLibraryAndState(Library library, String state,Pageable pageable);
	
}
