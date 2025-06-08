package cr.ac.una.quiniela.application.infraestructure.quiniela;

import cr.ac.una.quiniela.application.infraestructure.torneos.TorneoEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "quinielas")
public class QuinielaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "es_publica", nullable = false)
    private Boolean esPublica = false;

    @Column(name = "fecha_inicio", nullable = false)
    private Instant fechaInicio;

    @Column(name = "fecha_cierre", nullable = false)
    private Instant fechaCierre;

    @Column(name = "estado", nullable = false, length = 50)
    private String estado = "ABIERTA";

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_tipo_puntuacion", nullable = false)
    private TipoPuntuacionEntity idTipoPuntuacion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_torneo", nullable = false)
    private TorneoEntity idTorneo;
}
