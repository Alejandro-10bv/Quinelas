package cr.ac.una.quiniela.web.dto;

import lombok.Builder;

@Builder
public record PremioDto(
        Integer id,
        String nombre,
        String descripcion,
        String condiciones,
        Integer posicionRanking,
        Integer idQuiniela
) {}

