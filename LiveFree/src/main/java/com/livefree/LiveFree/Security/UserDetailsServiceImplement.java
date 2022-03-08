package com.livefree.LiveFree.Security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.livefree.LiveFree.Model.Usuario;
import com.livefree.LiveFree.Repository.UsuarioRepository;



@Service
public class UserDetailsServiceImplement implements UserDetailsService {
	
	
	
	   private @Autowired UsuarioRepository repository;

	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        
	        Optional<Usuario> optional = repository.findByEmail(username);

	        if (optional.isPresent()) {
	            return new UserDetailsImplement(optional.get());
	        } else {
	            throw new UsernameNotFoundException("User not found");
	        }
	    }

}
