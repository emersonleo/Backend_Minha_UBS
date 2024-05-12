package minhaubs.api.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import minhaubs.api.entity.Posto_Pessoas;

public interface PostoPessoaRepository extends JpaRepository<Posto_Pessoas,Long>{

        @Query("SELECT nome, fone FROM Posto_Pessoas as pp INNER JOIN Pessoa as p on p.id  = " + 
                "pp.pessoa.id where pp.posto.id = :postoId and  pp.id_tipo = 2")
        List<String> findAllPersonsByBHUId(@Param("postoId") Long BHUId);

        @Query("select id, nome FROM  Familia as f where f.id in" + 
                "(SELECT DISTINCT fp.familia.id as id_familia FROM Posto_Pessoas as pp INNER JOIN Familia_Pessoa as fp on fp.pessoa.id = pp.pessoa.id where pp.posto.id = 1 and pp.id_tipo = 2) ")
        List<String> findAllFamiliesByBHUId(@Param("postoId") Long BHUId);
}
