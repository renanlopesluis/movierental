package br.com.forall.movierental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.forall.movierental.entity.Director;
import br.com.forall.movierental.repository.DirectorRepository;

@Service
public class DirectorService {
	
	
	private DirectorRepository repository;
	
	@Autowired
	public DirectorService(DirectorRepository repository) {
		this.repository = repository;
	}
	
	public Director save(Director director) throws Exception {
		return this.repository.save(director);
	}
}
