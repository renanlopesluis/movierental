package br.com.forall.movierental.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.forall.movierental.entity.Director;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long>{ 
	
	 Optional<Director> findByName(String name);
}
