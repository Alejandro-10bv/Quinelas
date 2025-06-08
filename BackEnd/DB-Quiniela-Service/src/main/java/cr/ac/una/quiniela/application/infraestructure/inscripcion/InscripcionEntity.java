package cr.ac.una.quiniela.application.infraestructure.inscripcion;

import cr.ac.una.quiniela.application.infraestructure.quiniela.QuinielaEntity;
import cr.ac.una.quiniela.application.infraestructure.owners.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "inscripciones")
public class InscripcionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
        private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_usuario", nullable = false)
    private Usuario idUsuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_quiniela", nullable = false)
    private QuinielaEntity idQuiniela;

    @Column(name = "cumple_condiciones", nullable = false)
    private Boolean cumpleCondiciones = false;

    @Column(name = "acepta_reglas", nullable = false)
    private Boolean aceptaReglas = false;

}
