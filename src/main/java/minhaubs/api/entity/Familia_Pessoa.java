package minhaubs.api.entity;

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

@Table(name = "familia_pessoa")
@Entity(name = "Familia_Pessoa")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Familia_Pessoa {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_pessoa", nullable = false,  referencedColumnName = "id")
    private Pessoa pessoa;
    
    @OneToOne
    @JoinColumn(name = "id_familia", nullable = false, referencedColumnName = "id")
    private Familia familia;
}
