package minhaubs.api.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import minhaubs.api.entity.Visita;

public interface VisitaRepository extends JpaRepository<Visita,Long> {
    
    @Query("SELECT v FROM Visita as v WHERE (:posto IS NULL OR v.posto.id = :posto) AND " + 
     "(:agente IS NULL OR v.agente.id = :agente) AND (:dataInicio IS NULL OR v.dataHora >= :dataInicio) AND (:dataFim IS NULL OR v.dataHora <= :dataFim)")
    List<Visita> findByFiltro(@Param("posto") Long idUnit, @Param("agente") Long idAgent, @Param("dataInicio") LocalDateTime dataInicio, @Param("dataFim") LocalDateTime dataFim);
}
