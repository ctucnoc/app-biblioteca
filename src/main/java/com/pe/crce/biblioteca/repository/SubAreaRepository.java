package com.pe.crce.biblioteca.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pe.crce.biblioteca.model.SubArea;

@Repository
public interface SubAreaRepository extends JpaRepository<SubArea, Long>{

	public Optional<SubArea> findByIdAndState(Long id, String state);
	
	public Page<SubArea> findByDescriptionContainingAndState(String description, String state, Pageable pageable);
}
