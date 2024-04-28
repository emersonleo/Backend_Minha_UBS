package minhaubs.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import minhaubs.api.entity.Pessoa;
import java.util.List;


public interface PessoaRepository extends JpaRepository<Pessoa,Long>{
    List<Pessoa> findByCpf(String cpf);
}
