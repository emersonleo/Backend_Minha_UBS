package minhaubs.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import minhaubs.api.entity.Endereco;
import org.springframework.data.repository.query.Param;

public interface EnderecoRepository extends CrudRepository<Endereco, Long>{
    
    // @Query("SELECT v FROM Visita as v WHERE (:posto IS NULL OR v.posto.id = :posto) AND " + 
    //  "(:agente IS NULL OR v.pessoa.id = :agente) AND (:dataInicio IS NULL OR v.dataHora >= :dataInicio) AND (:dataFim IS NULL OR v.dataHora <= :dataFim)")
    @Query("SELECT DISTINCT e.id " + 
                "FROM Familia f " + 
                "INNER JOIN Familia_Pessoa fp ON f.id = fp.familia.id " + 
                "INNER JOIN Pessoa p ON p.id = fp.pessoa.id " + 
                "INNER join Endereco e on f.endereco.id = e.id " +
                "WHERE p.id = :pessoa")
    List<Endereco> findByPerson(@Param("pessoa") Long idPessoa);
}
