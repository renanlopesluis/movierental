package br.com.forall.movierental.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.forall.movierental.entity.Rental;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long>{
	
	@Query("select r from Rental r join r.movieCopy mc join mc.movie m join m.director d ")
	Optional<Rental> findById(Long id);

}
