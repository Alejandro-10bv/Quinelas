package cr.ac.una.quiniela.web.dto;

import cr.ac.una.quiniela.domain.entities.partidos.Equipo;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PartidoDto(Integer id, LocalDateTime fechaHora, Equipo equipoLocal, Equipo equipoVisitante, Integer golesLocal,
                         Integer golesVisitante, String estadio) {
}
