import { Module } from '@nestjs/common';
import { UsuarioController } from 'src/usuario/usuarios.controller';
import { UsuarioRepository } from './usuario.repository';

@Module({
    controllers: [UsuarioController],
    providers: [UsuarioRepository]
})
export class UsuarioModule {};