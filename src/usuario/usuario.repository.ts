import { Injectable } from "@nestjs/common";

@Injectable()
export class UsuarioRepository {
    private usuarios = [];

    async salvarUsuario(dadoUsuario){
        this.usuarios.push(dadoUsuario);
        return dadoUsuario;
    }

    async listarUsuarios(){
        return this.usuarios;
    }
}
