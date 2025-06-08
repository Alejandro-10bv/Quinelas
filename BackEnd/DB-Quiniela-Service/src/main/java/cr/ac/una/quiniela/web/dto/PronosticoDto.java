package cr.ac.una.quiniela.web.dto;

import lombok.Builder;
import java.time.Instant;

@Builder
public record PronosticoDto(
        Integer id,
        Integer idPartido,
        Integer idInscripcion,
        Integer golesLocal,
        Integer golesVisita,
        Instant fechaHora
) {
    public PronosticoDto withFechaHora(Instant nuevaFecha) {
        return new PronosticoDto(id, idPartido, idInscripcion, golesLocal, golesVisita, nuevaFecha);
    }

    public PronosticoDto withId(Integer nuevoId) {
        return new PronosticoDto(nuevoId, idPartido, idInscripcion, golesLocal, golesVisita, fechaHora);
    }
}