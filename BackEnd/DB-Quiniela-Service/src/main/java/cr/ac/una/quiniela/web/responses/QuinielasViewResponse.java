package cr.ac.una.quiniela.web.responses;

import java.util.List;

import cr.ac.una.quiniela.web.dto.QuinielaDto;
import lombok.Builder;

@Builder
public record QuinielasViewResponse(Integer totalQuinielas, List<QuinielaDto> quinielas) { }
