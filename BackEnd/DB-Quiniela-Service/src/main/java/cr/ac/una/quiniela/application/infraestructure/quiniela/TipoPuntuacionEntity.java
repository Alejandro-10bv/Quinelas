package cr.ac.una.quiniela.application.infraestructure.quiniela;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "tipos_puntuaciones",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"nombre", "puntos_obtenidos"})
    }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoPuntuacionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, nullable = false)
    private String nombre;

    @Column(name = "puntos_obtenidos", nullable = false)
    private int puntosObtenidos;
}
