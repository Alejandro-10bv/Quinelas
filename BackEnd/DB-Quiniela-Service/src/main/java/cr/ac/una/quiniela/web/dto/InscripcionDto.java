package cr.ac.una.quiniela.web.dto;

import lombok.Builder;
import java.util.UUID;

@Builder
public record InscripcionDto(
        Integer id,
        UUID usuarioId,
        Integer quinielaId,
        Boolean cumpleCondiciones,
        Boolean aceptaReglas
) {}
