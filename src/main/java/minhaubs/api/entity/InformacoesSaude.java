package minhaubs.api.entity;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table(name = "informacoes_saude")
@Entity(name = "informacoes_saude")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Embeddable
public class InformacoesSaude {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long tipo;
    private String descricaoTipo;
    private String descricaoInfo;
}
