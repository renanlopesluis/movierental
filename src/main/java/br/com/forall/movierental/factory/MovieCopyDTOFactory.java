package br.com.forall.movierental.factory;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.forall.movierental.api.V1.dto.MovieCopyDTO;
import br.com.forall.movierental.entity.MovieCopy;

@Component
public class MovieCopyDTOFactory implements Factory<MovieCopy, MovieCopyDTO> {
	
	private ModelMapper modelMapper;
	
	@Autowired
    public MovieCopyDTOFactory(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

	@Override
    public MovieCopyDTO build(MovieCopy movieCopy) {
    	MovieCopyDTO movieCopyDTO = modelMapper.map(movieCopy, MovieCopyDTO.class);
    	movieCopyDTO.setMovieCopyId(movieCopy.getId());
    	movieCopyDTO.setMovieTitle(movieCopy.getMovie().getTitle());
    	movieCopyDTO.setAvailable(movieCopy.isAvailable());
        return movieCopyDTO;
    }

}
