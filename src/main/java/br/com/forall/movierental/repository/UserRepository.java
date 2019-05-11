package br.com.forall.movierental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.forall.movierental.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findByName(String name);
	User findByEmail(String email);
		
}
