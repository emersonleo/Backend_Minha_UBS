package minhaubs.api.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "noticias")
@Entity(name = "Noticias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Embeddable
public class Noticia {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String noticia;

    @OneToOne
    @JoinColumn(name = "id_agente", nullable = false,  referencedColumnName = "id")
    private Pessoa agente;
    
    @OneToOne
    @JoinColumn(name = "id_posto", nullable = false,  referencedColumnName = "id")
    private Posto posto;

    @CreationTimestamp
    private LocalDateTime dataHora;
}
