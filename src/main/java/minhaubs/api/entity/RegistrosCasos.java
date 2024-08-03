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
import lombok.Setter;

@Table(name = "registros_casos")
@Entity(name = "registros_casos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class RegistrosCasos {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_pessoa", nullable = false,  referencedColumnName = "id")
    private Pessoa pessoa;

    @OneToOne
    @JoinColumn(name = "id_agente", nullable = false,  referencedColumnName = "id")
    private Pessoa agente;
    
    @OneToOne
    @JoinColumn(name = "id_posto", nullable = false,  referencedColumnName = "id")
    private Posto posto;

    @OneToOne
    @JoinColumn(name = "id_informacoes_saude", nullable = false,  referencedColumnName = "id")
    private InformacoesSaude info_saude;
}
