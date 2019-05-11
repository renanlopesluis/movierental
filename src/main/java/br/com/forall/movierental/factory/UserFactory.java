package br.com.forall.movierental.factory;

import org.hibernate.procedure.NoSuchParameterException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.forall.movierental.api.V1.dto.UserDTO;
import br.com.forall.movierental.entity.User;
import lombok.NonNull;

@Component
public class UserFactory implements Factory<UserDTO, User>{
	
	@Override
	public User build(@NonNull UserDTO dto) throws NoSuchParameterException {
		validate(dto);
        User user = User.builder()
        		.id(dto.getUserId())
                .name(dto.getName())
                .email(dto.getEmail())
                .password(new BCryptPasswordEncoder().encode(dto.getPassword()))
                .build();
        return user;
	}
	
	private void validate(UserDTO dto) throws NoSuchParameterException {
        validateName(dto.getName());
        validateEmail(dto.getEmail());
        validatePassword(dto.getPassword());
    }

    private void validateName(String name) throws NoSuchParameterException {
        if (name == null) {
            throw new NoSuchParameterException("You should inform a user name");
        }
	}
    
    private void validateEmail(String email) throws NoSuchParameterException {
        if (email == null) {
            throw new NoSuchParameterException("You should inform an email");
        }
	}
    
    private void validatePassword(String password) throws NoSuchParameterException {
        if (password == null) {
            throw new NoSuchParameterException("You should inform a password");
        }
	}

}
