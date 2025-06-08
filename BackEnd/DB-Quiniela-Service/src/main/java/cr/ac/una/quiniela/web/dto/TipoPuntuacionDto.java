package cr.ac.una.quiniela.web.dto;

import lombok.Builder;

@Builder
public record TipoPuntuacionDto(
    Integer id,
    String nombre,
    Integer puntosObtenidos
) {}
