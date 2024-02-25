package minhaubs.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import minhaubs.api.entity.Usuario;
import java.util.List;


public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
    
    List<Usuario> findByEmail(String email);
}
