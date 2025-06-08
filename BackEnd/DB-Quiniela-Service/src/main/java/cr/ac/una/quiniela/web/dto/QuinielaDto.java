package cr.ac.una.quiniela.web.dto;

import lombok.Builder;
import java.time.Instant;

@Builder
public record QuinielaDto(
        Integer id,
        String nombre,
        String descripcion,
        Boolean esPublica,
        Instant fechaInicio,
        Instant fechaCierre,
        String estado,
        Integer idTipoPuntuacion,
        Integer idTorneo
) {}