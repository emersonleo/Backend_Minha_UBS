package minhaubs.api.controller;


import minhaubs.api.DTO.UsuarioDTO;
import minhaubs.api.infra.Constants;
import minhaubs.api.services.UsuarioService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("usuario")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    public String validarUsuario(String userCpf) {
        return this.usuarioService.getIdProfissional(userCpf);
    }

    public String vinculoUsuario(String ProfessionalId, String userCpf) {
        return this.usuarioService.getVinculosAtivos(ProfessionalId, userCpf);
    }

    @PostMapping(path="/cadastrar")
    public String cadastrar(@RequestBody Map<String, String> userData){

        String ProfessionalId = userData.get(Constants.ID_PROFISSIONAL);
        String userCpf = userData.get(Constants.CPF);

        System.out.println(this.validarUsuario(userCpf));
        System.out.println(this.vinculoUsuario(ProfessionalId, userCpf));

        return "";

    }
}
