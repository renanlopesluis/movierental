package br.com.forall.movierental.dataprovider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.forall.movierental.entity.User;
import br.com.forall.movierental.repository.UserRepository;

@Component
public class UserDataProvider {
	
	private UserRepository repository;

    @Autowired
    public UserDataProvider(UserRepository repository) {
        this.repository = repository;
    }    
    
    public User build() {
        User user = User.builder()
        		.name("Renan")
        		.email("renan@gmail.com")
        		.password("123456")
        		.build();
        return repository.save(user);
    }

    public void deleteAll() {
		this.repository.deleteAll();
	}
    
}
