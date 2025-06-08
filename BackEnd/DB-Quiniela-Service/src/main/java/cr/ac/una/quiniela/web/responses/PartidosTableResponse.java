package cr.ac.una.quiniela.web.responses;

import cr.ac.una.quiniela.web.dto.PartidoDto;
import lombok.Builder;

import java.util.List;

@Builder
public record PartidosTableResponse(Integer totalMatches, List<PartidoDto> partidos) {
}
