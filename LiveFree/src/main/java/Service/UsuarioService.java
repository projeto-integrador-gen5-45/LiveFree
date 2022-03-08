package Service;

import java.nio.charset.Charset;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.livefree.LiveFree.Model.Usuario;
import com.livefree.LiveFree.Repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	
	private @Autowired UsuarioRepository repository;
    private Usuario newUsuario;

 
    public ResponseEntity<Usuario> registerVoter(UsuarioRegisterDTO usuario) {
        Optional<Usuario> optional = repository.findByEmail(usuario.getEmail());

        if (optional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
        } else {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		    usuario.setSenha(encoder.encode(usuario.getSenha()));

            newUsuario = new Usuario(
                            usuario.getNome(),
                            usuario.getEmail(),
                            usuario.getSenha());
                            
            return ResponseEntity.status(201).body(repository.save(newUsuario));
        }
    }

  
    public ResponseEntity<CredentialsDTO> validCredentials(@Valid UsuarioLoginDTO usuario) {
        return repository.findByEmail(usuario.getEmail())
                .map(v -> {
                    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                    if (encoder.matches(usuario.getSenha(), v.getSenha())) {
                        CredentialsDTO credentials = new CredentialsDTO(
                                v.getId(),
                                v.getNome(),
                                v.getEmail(),
                                generatorBasicToken(usuario.getEmail(), usuario.getSenha()));
                        return ResponseEntity.status(200).body(credentials);
                    } else {
                        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid password");
                    }
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Email not found"));
    }


    private static String generatorBasicToken(String email, String senha) {
        String structure = email + ":" + senha;
        byte[] structureBase64 = Base64.encodeBase64(structure.getBytes(Charset.forName("US-ASCII")));
        return "Basic " + new String(structureBase64);
    }
	
	

}
