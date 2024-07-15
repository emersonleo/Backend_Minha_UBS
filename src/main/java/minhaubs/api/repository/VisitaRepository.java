package minhaubs.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import minhaubs.api.entity.Visita;

public interface VisitaRepository extends JpaRepository<Visita,Long> {
    
}
