package cr.ac.una.quiniela.domain.useCases;

import cr.ac.una.quiniela.web.dto.TipoPuntuacionDto;
import java.util.List;

public interface ITipoPuntuacionService {
    List<TipoPuntuacionDto> obtenerTodosTiposPuntuacion();
    TipoPuntuacionDto obtenerTipoPuntuacionPorId(Integer id);
}
