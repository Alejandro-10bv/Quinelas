package cr.ac.una.quiniela.application.infraestructure.pronostico;

import cr.ac.una.quiniela.application.infraestructure.inscripcion.InscripcionEntity;
import cr.ac.una.quiniela.application.infraestructure.partidos.PartidoEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "pronosticos")
@Getter
@Setter
public class PronosticoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_partido", nullable = false)
    private PartidoEntity idPartido;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_inscripcion", nullable = false)
    private InscripcionEntity idInscripcion;

    @Column(name = "goles_local", nullable = false)
    private Integer golesLocal;

    @Column(name = "goles_visita", nullable = false)
    private Integer golesVisita;

    @Column(name = "fecha_hora", nullable = false)
    private Instant fechaHora;
}
