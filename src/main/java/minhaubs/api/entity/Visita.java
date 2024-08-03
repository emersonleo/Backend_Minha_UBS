package minhaubs.api.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

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

@Table(name = "visita")
@Entity(name = "Visita")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Visita {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_familia", nullable = false)
    private Familia familia;

    @OneToOne
    @JoinColumn(name = "agente", nullable = false)
    private Pessoa pessoa;

    // @OneToOne
    // @JoinColumn(name = "posto", nullable = false)
    // private Posto posto;

    @CreationTimestamp
    private LocalDateTime dataHora;


}