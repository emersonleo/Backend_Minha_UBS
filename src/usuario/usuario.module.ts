import { Module } from '@nestjs/common';
import { UsuarioController } from 'src/usuario/usuarios.controller';
import { UsuarioRepository } from './usuario.repository';
import { UsuarioService } from './usuario.service';
import { HttpModule } from '@nestjs/axios';

@Module({
    imports: [HttpModule],
    controllers: [UsuarioController],
    providers: [UsuarioRepository, UsuarioService]
})
export class UsuarioModule {};