package cr.ac.una.quiniela.application.cases.torneos;

import cr.ac.una.quiniela.application.infraestructure.torneos.TorneoEntity;
import cr.ac.una.quiniela.application.infraestructure.torneos.TorneoRepository;
import cr.ac.una.quiniela.domain.entities.torneos.Torneo;
import cr.ac.una.quiniela.domain.useCases.ITorneosService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TorneosServiceImpl implements ITorneosService {

    private final TorneoRepository torneoRepository;

    @Override
    public Page<Torneo> getAllTorneos(Pageable pageable) {
        Page<TorneoEntity> torneos = torneoRepository.findAll(pageable);

        return torneos.map(torneoEntity -> Torneo.builder()
                .id(torneoEntity.getId())
                .nombre(torneoEntity.getNombre())
                .descripcion(torneoEntity.getDescripcion())
                .build());
    }

    @Override
    public Integer getTotalTorneos() {
        return Math.toIntExact(torneoRepository.count());
    }
}
