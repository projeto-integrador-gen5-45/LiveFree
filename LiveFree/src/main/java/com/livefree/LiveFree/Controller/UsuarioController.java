package com.livefree.LiveFree.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.livefree.LiveFree.Model.UserLogin;
import com.livefree.LiveFree.Model.Usuario;
import com.livefree.LiveFree.Repository.UsuarioRepository;
import com.livefree.LiveFree.Service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository repository;


	@SuppressWarnings("rawtypes")
	@PostMapping("/logar")
	public ResponseEntity Autentication(@RequestBody Optional<UserLogin> user){
		return usuarioService.Logar(user);
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/cadastrar")
	public ResponseEntity post(@RequestBody Usuario usuario){
		return usuarioService.CadastrarUsuario(usuario);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Usuario>> getAll() {

		return ResponseEntity.ok(repository.findAll());
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Usuario> put(@RequestBody Usuario usuario){
		return ResponseEntity.ok(repository.save(usuario));
	}


	
	
}
