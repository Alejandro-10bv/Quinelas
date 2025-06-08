package cr.ac.una.quiniela.domain.useCases;

import cr.ac.una.quiniela.web.dto.PremioDto;
import java.util.List;

public interface IPremioService {
    PremioDto crearPremio(PremioDto premioDto);
    List<PremioDto> obtenerPremiosPorQuiniela(Integer quinielaId);
    void eliminarPremio(Integer id);
}
