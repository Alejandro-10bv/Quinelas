package cr.ac.una.quiniela.application.infraestructure.torneos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Getter
@Setter
@Entity
@Table(name = "torneos")
public class TorneoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Nationalized
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

}
