package br.com.forall.movierental.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.forall.movierental.entity.User;
import br.com.forall.movierental.repository.UserRepository;

@Component
public class LoginService implements UserDetailsService{
	
	private UserRepository userRepository;
	
	@Autowired
    private AuthenticationManager authenticationManager;
		
	@Autowired
	public LoginService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void logon(String email, String password) {
		UserDetails userDetails = this.loadUserByUsername(email);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        authenticationManager.authenticate(authenticationToken);

        if (authenticationToken.isAuthenticated()) 
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
	
	public String findLoggedInUsername() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            return ((UserDetails)userDetails).getUsername();
        }

        return null;
    }
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = Optional.ofNullable(userRepository.findByEmail(email)).orElseThrow(()-> new UsernameNotFoundException("User not found!"));
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList("USER"));
	}
	

}
