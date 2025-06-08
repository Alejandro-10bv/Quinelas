package cr.ac.una.quiniela.domain.entities.quiniela;

import lombok.Builder;

import java.util.UUID;

@Builder
public record Inscripcion(Integer id,
                          UUID idUsuario,
                          Integer idQuiniela,
                          Boolean cumpleCondiciones,
                          Boolean aceptaReglas) {
}
