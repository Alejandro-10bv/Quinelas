package cr.ac.una.quiniela.application.cases.tipoPuntuacion;

import cr.ac.una.quiniela.application.infraestructure.quiniela.TipoPuntuacionEntity;
import cr.ac.una.quiniela.application.infraestructure.quiniela.TipoPuntuacionRepository;
import cr.ac.una.quiniela.domain.useCases.ITipoPuntuacionService;
import cr.ac.una.quiniela.web.dto.TipoPuntuacionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TipoPuntuacionServiceImpl implements ITipoPuntuacionService {
    private final TipoPuntuacionRepository tipoPuntuacionRepository;

    @Override
    public List<TipoPuntuacionDto> obtenerTodosTiposPuntuacion() {
        return tipoPuntuacionRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TipoPuntuacionDto obtenerTipoPuntuacionPorId(Integer id) {
        return tipoPuntuacionRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Tipo de puntuación no encontrado"));
    }

    private TipoPuntuacionDto toDto(TipoPuntuacionEntity entity) {
        return TipoPuntuacionDto.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .puntosObtenidos(entity.getPuntosObtenidos())
                .build();
    }
}
