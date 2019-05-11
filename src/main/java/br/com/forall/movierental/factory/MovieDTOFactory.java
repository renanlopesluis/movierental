package br.com.forall.movierental.factory;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.forall.movierental.api.V1.dto.MovieDTO;
import br.com.forall.movierental.entity.Movie;
import lombok.NonNull;

@Component
public class MovieDTOFactory implements Factory<Movie, MovieDTO>{
	
	private ModelMapper modelMapper;

    @Autowired
    public MovieDTOFactory(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public MovieDTO build(@NonNull Movie movie) {
    	MovieDTO movieDTO = modelMapper.map(movie, MovieDTO.class);
    	movieDTO.setMovieId(movie.getId());
        movieDTO.setTitle(movie.getTitle());
        movieDTO.setDirectorName(movie.getDirector().getName());
        return movieDTO;
    }
    
    public List<MovieDTO> build(List<Movie> movies) {
    	List<MovieDTO> dtos = new ArrayList<MovieDTO>();
    	for(Movie movie : movies) {
    		dtos.add(this.build(movie));
    	}
        return dtos;
    }   
    
}
