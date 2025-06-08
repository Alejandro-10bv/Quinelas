package cr.ac.una.quiniela.domain.useCases;

import cr.ac.una.quiniela.web.dto.PronosticoDto;
import java.util.List;

public interface IPronosticoService {
    PronosticoDto crearPronostico(PronosticoDto pronosticoDto);
    PronosticoDto actualizarPronostico(PronosticoDto pronosticoDto);
    List<PronosticoDto> obtenerPronosticosPorInscripcion(Integer idInscripcion);
    PronosticoDto obtenerPronosticoPorPartidoYInscripcion(Integer idPartido, Integer idInscripcion);
    void eliminarPronostico(Integer id);
}
