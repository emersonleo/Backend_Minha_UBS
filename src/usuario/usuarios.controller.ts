/* eslint-disable prettier/prettier */
import { Body, Controller, Post, Get } from '@nestjs/common';
import { UsuarioRepository } from './usuario.repository';

@Controller('/usuarios')
export class UsuarioController {

    constructor(private usuarioRepository: UsuarioRepository){}
    
    @Post()
    async criarUsuario(@Body() dadosUsuario) {
        return this.usuarioRepository.salvarUsuario(dadosUsuario);
    }

    @Get()
    async listarUsuarios() {
        return this.usuarioRepository.listarUsuarios();
    }
}
