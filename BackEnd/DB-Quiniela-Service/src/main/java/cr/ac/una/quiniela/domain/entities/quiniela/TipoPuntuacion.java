package cr.ac.una.quiniela.domain.entities.quiniela;

import lombok.Builder;

@Builder
public record TipoPuntuacion(
    Integer id,
    String nombre,
    Integer puntosObtenidos
) {}
