package cr.ac.una.quiniela.domain.entities.partidos;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record Partido(Integer id, LocalDateTime fechaHora, Equipo equipoLocal, Equipo equipoVisitante, Integer golesLocal,
                      Integer golesVisitante, String estadio) {
}
