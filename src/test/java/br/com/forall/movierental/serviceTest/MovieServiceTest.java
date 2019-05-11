package br.com.forall.movierental.serviceTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.hibernate.procedure.NoSuchParameterException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.forall.movierental.api.V1.dto.MovieDTO;
import br.com.forall.movierental.dataprovider.DirectorDataProvider;
import br.com.forall.movierental.dataprovider.MovieCopyDataProvider;
import br.com.forall.movierental.entity.Movie;
import br.com.forall.movierental.entity.MovieCopy;
import br.com.forall.movierental.factory.MovieDTOFactory;
import br.com.forall.movierental.factorytest.MovieFactoryTest;
import br.com.forall.movierental.service.MovieService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MovieServiceTest {
	
	@Autowired
	private MovieService service;
	
	@Autowired
	private DirectorDataProvider directorDataProvider;
	
	@Autowired
	private MovieCopyDataProvider movieCopyDataProvider;
	
	@Autowired
	private MovieDTOFactory DTOFactory;
	
	@Autowired
	private MovieFactoryTest factory;
			
	@Before
	public void init() {
		clean();
	}
	
	@Test
	public void shouldInsertAMovie(){
		MovieDTO dto = DTOFactory.build(factory.build());
		Movie movie = null;
		
		movie = service.save(dto);

		Assert.assertNotNull(movie.getId());		
	}
	
	@Test(expected = NoSuchParameterException.class)
	public void shouldNotInsertATitlelessMovie() {
		MovieDTO dto = DTOFactory.build(factory.build());
	
		dto.setTitle(null);
		service.save(dto);
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void shouldNotInsertAMovieWithNoDirector() {
		MovieDTO dto = DTOFactory.build(factory.build());
		
		dto.setDirectorName(null);
		service.save(dto);
	}
	
	@Test
	public void shouldFindAMovieByTitle(){
		MovieDTO dto = DTOFactory.build(factory.build());
		service.save(dto);
		Optional<Movie> optionalMovie = service.findByTitle(dto.getTitle());
		Movie movie = optionalMovie.orElse(null);
	
		Assert.assertNotNull(movie);
		
	}	
	
	@Test
	public void shouldNotFindAnyMovieIfItDoesntExist() {
		MovieDTO dto = DTOFactory.build(factory.build());
		service.save(dto);
		Optional<Movie> optionalMovie = service.findByTitle("FightClub");		
		
		Assert.assertNull(optionalMovie.orElse(null));
	}	
	
	@Test(expected = Exception.class)
	public void shouldNotFindAMovieIfMissingTitle() {
		MovieDTO dto = DTOFactory.build(factory.build());
		
		service.save(dto);
		service.findByTitle(null);
	}	
	
	@Test
	public void shouldFindAvailableMovies() {
		MovieDTO dto = DTOFactory.build(factory.build());
		List<Movie> movies = new ArrayList<Movie>();
		Movie movie = service.save(dto);
		attachCopies(movie, Boolean.TRUE);
		movies = service.findAllAvailable();
	
		Assert.assertEquals(movies.size(), 1);
		
	}	
	
	@Test
	public void shouldNotFindAvailableMovies() {
		MovieDTO dto = DTOFactory.build(factory.build());
		
		Movie movie = service.save(dto);
		attachCopies(movie, Boolean.FALSE);
		List<Movie> movies  = service.findAllAvailable();
		
		Assert.assertTrue(movies.isEmpty());
	}	
	
	private void clean() {
		movieCopyDataProvider.deleteAll();
		service.getRepository().deleteAll();
		directorDataProvider.deleteAll();
	}
		
	private void attachCopies(Movie movie, Boolean availability) {
		List<MovieCopy>copies = new ArrayList<MovieCopy>();
		copies.add(movieCopyDataProvider.build(movie, availability));
		
	}


}
