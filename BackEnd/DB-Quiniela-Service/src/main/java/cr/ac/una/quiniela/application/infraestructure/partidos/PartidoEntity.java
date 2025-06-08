package cr.ac.una.quiniela.application.infraestructure.partidos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "partidos")
public class PartidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "fecha_hora", nullable = false)
    private Instant fechaHora;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_equipo_local", nullable = false)
    private EquipoEntity idEquipoLocal;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_equipo_visitante", nullable = false)
    private EquipoEntity idEquipoVisitante;

    @Column(name = "goles_local")
    private Integer golesLocal;

    @Column(name = "goles_visita")
    private Integer golesVisita;

    @Column(name = "ID_torneo")
    private Integer idTorneo;

}
