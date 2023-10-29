/* eslint-disable @typescript-eslint/no-unused-vars */
/* eslint-disable prettier/prettier */
import { Body, Controller, Post, Get } from '@nestjs/common';
import { UsuarioRepository } from './usuario.repository';
import { UsuarioService } from './usuario.service';

@Controller('/usuarios')
export class UsuarioController {

    constructor(private usuarioRepository: UsuarioRepository,
        private usuarioService: UsuarioService){}
    
    @Post()
    async criarUsuario(@Body() dadosUsuario) {
        return this.usuarioRepository.salvarUsuario(dadosUsuario);
    }

    @Get()
    async listarUsuarios() {
        return this.usuarioRepository.listarUsuarios();
    }

    @Post('/validar')
    async validarUsuario(@Body() usuario){  
        const idProfissional = await this.usuarioService.getIdProfissional(usuario?.cpf);

        if(idProfissional.length == 0){
            return {mensagem:"Usuário não está cadastrado como agente"};
        }

        return idProfissional;
    }

    @Post('/vinculos')
    async vinculoUsuario(@Body() usuario){  
        const vinculos = await this.usuarioService.getVinculosAtivos(usuario?.idProfissional,usuario?.cpf);
        const temVinculo = vinculos.filter(vinculo => vinculo.cbo == 515105).length > 0;
        if(!temVinculo){
            return {mensagem:"Usuário não está vinculado como agente"};
        }
        return vinculos;
    }
}


