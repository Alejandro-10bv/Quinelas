package cr.ac.una.quiniela.domain.useCases;

import cr.ac.una.quiniela.domain.entities.partidos.Partido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPartidosService {
    Page<Partido> getAllPartidos(Pageable pageable);
    Page<Partido> getPartidosByTorneoId(Integer torneoId, Pageable pageable);
    Integer getTotalPartidosByTorneoId(Integer torneoId);
    Integer getTotalPartidos(Pageable pageable);
    List<Partido> getAllPartidoByTorneoId(Integer torneoId);
    void updatePartidoById(Integer idPartido, Integer golVisita, Integer golLocal);
}
