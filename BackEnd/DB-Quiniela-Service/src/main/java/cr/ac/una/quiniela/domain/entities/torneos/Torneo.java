package cr.ac.una.quiniela.domain.entities.torneos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
public record Torneo(Integer id, String nombre, String descripcion) {
}
