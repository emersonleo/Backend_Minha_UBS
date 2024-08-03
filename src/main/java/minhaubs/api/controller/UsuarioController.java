package minhaubs.api.controller;


import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import minhaubs.api.entity.Pessoa;
import minhaubs.api.entity.Usuario;
import minhaubs.api.infra.Utils;
import minhaubs.api.repository.PessoaRepository;
import minhaubs.api.repository.UsuarioRepository;
import minhaubs.api.services.UsuarioService;

@CrossOrigin
@RestController
@RequestMapping("usuario")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
	private PessoaRepository pessoaRepository;
    
    @Autowired
	private UsuarioRepository usuarioRepository;
    
    public String validarUsuario(String userCpf) {
        return this.usuarioService.getIdProfissional(userCpf);
    }

    public String vinculoUsuario(String ProfessionalId, String userCpf) {
        return this.usuarioService.getVinculosAtivos(ProfessionalId, userCpf);
    }

    @CrossOrigin("http://localhost:8080")
    @PostMapping(path="/cadastrar")
    public String cadastrar(@RequestBody Map<String, String> userData) throws NoSuchAlgorithmException{

        //String ProfessionalId = userData.get(Constants.ID_PROFISSIONAL);
        //String userCpf = userData.get(Constants.CPF);

        String cpf = userData.get("cpf");
        String fone = userData.get("fone");
        Pessoa person = new Pessoa(123L, userData.get("nome"),cpf, fone);
        
        System.out.println(cpf);
        
        String email = userData.get("email");
        String senha = Utils.passwordMd5Encode(userData.get("senha")); //
        
        if(pessoaRepository.findByCpf(cpf).size() > 0 || 
        		usuarioRepository.findByEmail(email).size() > 0) {
        	return "Pessoa já existe";
        }
        
        Pessoa newPerson = pessoaRepository.save(person);
        
        Usuario user = new Usuario(123L, email, senha, newPerson);
        
        usuarioRepository.save(user);
        
        return "Usuario Cadastrado com Sucesso: " + user.getEmail();

    }
    
    @SuppressWarnings("rawtypes")
    @CrossOrigin("http://localhost:8080")
    @PostMapping(path="/login")
    public ResponseEntity login(@RequestBody Map<String, String> userDataLogin) throws NoSuchAlgorithmException{
    	
    	Usuario userAuthenticated = null;
    	List<Usuario> listUserAuthenticated = usuarioRepository.findByEmail(
    			userDataLogin.get("email"));
        String encryptedPassword = Utils.passwordMd5Encode(userDataLogin.get("senha"));
        
        if(listUserAuthenticated.size() > 0) {
        	userAuthenticated = listUserAuthenticated.get(0);
        }else {
        	return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); 
        }
        
        if(userAuthenticated != null &&
        		userAuthenticated.getSenha().equals(encryptedPassword)){
                    return new ResponseEntity<>(userAuthenticated,HttpStatus.OK); 
        }
        
    	return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    	
    }
}
