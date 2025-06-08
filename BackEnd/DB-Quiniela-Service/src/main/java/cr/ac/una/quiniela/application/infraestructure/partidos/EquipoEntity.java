package cr.ac.una.quiniela.application.infraestructure.partidos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Getter
@Setter
@Entity
@Table(name = "equipos")
public class EquipoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @OneToOne(mappedBy = "idEquipo")
    private EstadioEntity estadioEntity;

}
