package cr.ac.una.quiniela.domain.entities.quiniela;

import cr.ac.una.quiniela.domain.entities.torneos.Torneo;
import lombok.Builder;

import java.time.Instant;

@Builder
public record Quiniela(Integer id,
                       String nombre,
                       String descripcion,
                       Boolean esPublica,
                       Instant fechaInicio,
                       Instant fechaCierre,
                       String estado,
                       TipoPuntuacion tipoPuntuacion,
                       Torneo torneoId) {


}
