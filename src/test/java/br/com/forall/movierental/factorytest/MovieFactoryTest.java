package br.com.forall.movierental.factorytest;

import java.util.ArrayList;

import org.hibernate.procedure.NoSuchParameterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.forall.movierental.dataprovider.DirectorDataProvider;
import br.com.forall.movierental.entity.Movie;
import br.com.forall.movierental.entity.MovieCopy;

@Component
public class MovieFactoryTest {
	
	@Autowired
	private DirectorDataProvider directorDataProvider;
		
	public Movie build() throws NoSuchParameterException {
        Movie movie = Movie.builder()
        		.title("Reservoir Dogs")
                .director(directorDataProvider.build())
                .copies(new ArrayList<MovieCopy>())
                .build();
      
        return movie;
	}

}
