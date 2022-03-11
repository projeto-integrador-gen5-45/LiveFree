package com.livefree.LiveFree.Service;

import java.nio.charset.Charset;
import java.util.Optional;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.livefree.LiveFree.Model.UserLogin;
import com.livefree.LiveFree.Model.Usuario;
import com.livefree.LiveFree.Repository.UsuarioRepository;
import com.livefree.LiveFree.exceptions.models.EmailExsitException;
import com.livefree.LiveFree.exceptions.models.EmailNotExistException;
import com.livefree.LiveFree.exceptions.models.WrongPasswordException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	public ResponseEntity<Usuario> CadastrarUsuario(Usuario usuario) {
		if (repository.findByEmail(usuario.getEmail()).isPresent()) {
			throw new EmailExsitException(usuario.getEmail());
		}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);
		
		return ResponseEntity.status(201).body(repository.save(usuario));
	}
	
	public ResponseEntity<UserLogin> Logar(Optional<UserLogin> user){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = repository.findByEmail(user.get().getUsuario());
		
		if(usuario.isPresent()) {
			if(encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {
				
				String auth = user.get().getUsuario() + ":" + user.get().getSenha();
				byte[] encodeAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodeAuth);
				
				user.get().setToken(authHeader);
				user.get().setNome(usuario.get().getNome());
				user.get().setSenha(usuario.get().getSenha());
				
				return ResponseEntity.ok(user.get());
			}
			throw new WrongPasswordException();
		}
		throw new EmailNotExistException(user.get().getUsuario());
	}
	

}
