package cr.ac.una.quiniela.domain.useCases;

import cr.ac.una.quiniela.domain.entities.torneos.Torneo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITorneosService {
    Page<Torneo> getAllTorneos(Pageable pageable);

    Integer getTotalTorneos();
}
