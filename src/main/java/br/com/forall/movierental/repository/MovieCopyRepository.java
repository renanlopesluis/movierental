package br.com.forall.movierental.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.forall.movierental.entity.MovieCopy;

@Repository
public interface MovieCopyRepository  extends JpaRepository<MovieCopy, Long>{

	Optional<MovieCopy> findById(Long id);
	
	@Query("select mc from MovieCopy mc join mc.movie m join m.director d where mc.available = true and m.id = :movieId order by mc.id")
	List<MovieCopy>findAvailablesByMovieId(@Param("movieId") Long movieId);
}
