package cr.ac.una.quiniela.application.infraestructure.premio;

import cr.ac.una.quiniela.application.infraestructure.quiniela.QuinielaEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "premios")
public class PremioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "condiciones", nullable = false)
    private String condiciones;

    @Column(name = "posicion_ranking", nullable = false)
    private Integer posicionRanking;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_quiniela", nullable = false)
    private QuinielaEntity idQuiniela;
}
