package br.com.forall.movierental.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.forall.movierental.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{
	
	Optional<Movie>findByTitle(String title);
	
	@Query("select distinct m from Movie m join m.copies mc on m.id = mc.movie.id where mc.available = true")
	List<Movie> findAllAvailable();
}
