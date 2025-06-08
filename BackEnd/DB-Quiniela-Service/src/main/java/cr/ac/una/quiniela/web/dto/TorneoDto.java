package cr.ac.una.quiniela.web.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record TorneoDto(Integer id, String nombre, String descripcion) {
}
