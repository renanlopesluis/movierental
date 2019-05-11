package br.com.forall.movierental.dataprovider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.forall.movierental.entity.Director;
import br.com.forall.movierental.repository.DirectorRepository;

@Component
public class DirectorDataProvider {
	
	private DirectorRepository repository;

    @Autowired
    public DirectorDataProvider(DirectorRepository repository) {
        this.repository = repository;
    }

    public Director build() {
        Director director = Director.builder()
        		.name("Quentin Tarantino")
        		.build();
        return this.repository.save(director);
    }
    
    public void deleteAll() {
		this.repository.deleteAll();
	}

}
