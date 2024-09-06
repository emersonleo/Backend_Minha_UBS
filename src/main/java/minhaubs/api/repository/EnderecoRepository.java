package minhaubs.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import minhaubs.api.entity.Endereco;
import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
    
    // @Query("SELECT v FROM Visita as v WHERE (:posto IS NULL OR v.posto.id = :posto) AND " + 
    //  "(:agente IS NULL OR v.pessoa.id = :agente) AND (:dataInicio IS NULL OR v.dataHora >= :dataInicio) AND (:dataFim IS NULL OR v.dataHora <= :dataFim)")
    @Query("SELECT DISTINCT e " + 
                "FROM Familia f " + 
                "INNER JOIN Familia_Pessoa fp ON f.id = fp.familia.id " + 
                "INNER JOIN Pessoa p ON p.id = fp.pessoa.id " + 
                "INNER join Endereco e on f.endereco.id = e.id " +
                "WHERE p.id = :pessoa")
    Optional<Endereco> findByPerson(@Param("pessoa") Long idPessoa);

    @Query(value = "SELECT e.* FROM registros_casos rc INNER JOIN " +
    "endereco e ON e.id  = rc.id_endereco where rc.id_posto = :posto " +
    "and rc.id_agente = :agente and rc.id_informacoes_saude = :caso", 
    nativeQuery = true)
    List<Endereco> findAddressByCases(@Param("agente") Long agent, @Param("posto") Long unit,
    @Param("caso") Long idCase);
    
}
