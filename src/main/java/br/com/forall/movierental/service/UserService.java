package br.com.forall.movierental.service;

import org.hibernate.procedure.NoSuchParameterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.forall.movierental.api.V1.dto.UserDTO;
import br.com.forall.movierental.entity.User;
import br.com.forall.movierental.factory.UserFactory;
import br.com.forall.movierental.repository.UserRepository;
import lombok.NonNull;

@Service
public class UserService {
		
	private UserRepository repository;
	private UserFactory factory;
	
	@Autowired
	public UserService (UserRepository repository, UserFactory factory) {
		this.repository = repository;
		this.factory = factory;
	}
	
	public UserRepository getRepository() {
		return this.repository;
	}

	public User save(UserDTO dto) throws NoSuchParameterException {
		User user = factory.build(dto);
		return this.repository.save(user);
	}
	
	public User findByEmail(@NonNull String email){
		return this.repository.findByEmail(email);
	}
	
}
