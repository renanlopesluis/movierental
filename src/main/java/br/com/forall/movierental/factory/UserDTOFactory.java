package br.com.forall.movierental.factory;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.forall.movierental.api.V1.dto.UserDTO;
import br.com.forall.movierental.entity.User;
import lombok.NonNull;

@Component
public class UserDTOFactory implements Factory<User, UserDTO>{
	
	private ModelMapper modelMapper;
	
	@Autowired
    public UserDTOFactory(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

	@Override
	public UserDTO build(@NonNull User user) {
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		userDTO.setUserId(user.getId());
    	userDTO.setName(user.getName());
    	userDTO.setEmail(user.getEmail());
        return userDTO;
	}

}
