package br.com.forall.movierental.factorytest;

import org.springframework.stereotype.Component;

import br.com.forall.movierental.entity.User;

@Component
public class UserFactoryTest {

	public User build() {
		 User user = User.builder()
			.name("Renan")
			.email("renan@gmail.com")
			.password("123456").build();
	        return user;
	}
	
	public User buildNamelessUser() {
		 User user = User.builder()
			.email("renan@gmail.com")
			.password("123456").build();
	        return user;
	}
	
	public User buildUserWithNoPassword() {
		 User user = User.builder()
			.name("Renan")
			.email("renan@gmail.com").build();
	        return user;
	}
	
	public User buildUserWithNoEmail() {
		 User user = User.builder()
			.name("Renan")
			.password("123456").build();
	        return user;
	}
}
