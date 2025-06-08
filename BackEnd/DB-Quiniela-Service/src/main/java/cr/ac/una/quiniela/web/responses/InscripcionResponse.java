package cr.ac.una.quiniela.web.responses;

import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Builder
public record InscripcionResponse(Integer idInscripcion,
                                  String nombreQuiniela,
                                  List<Integer> idPartidos) {
}
