package minhaubs.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import minhaubs.api.entity.Noticia;
import minhaubs.api.entity.Posto;


public interface NoticiaRepository extends JpaRepository<Noticia,Long>  {
 
    List<Noticia> findByPosto(Posto unit);
}
