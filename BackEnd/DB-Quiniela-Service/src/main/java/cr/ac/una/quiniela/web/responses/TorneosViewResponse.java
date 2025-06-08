package cr.ac.una.quiniela.web.responses;

import cr.ac.una.quiniela.web.dto.TorneoDto;
import lombok.Builder;

import java.util.List;

@Builder
public record TorneosViewResponse(Integer totalTorneos, List<TorneoDto> torneos) {
}
