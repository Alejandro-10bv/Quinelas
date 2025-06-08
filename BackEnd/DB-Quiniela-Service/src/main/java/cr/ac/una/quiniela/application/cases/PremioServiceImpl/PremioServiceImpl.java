package cr.ac.una.quiniela.application.cases.PremioServiceImpl;

import cr.ac.una.quiniela.application.infraestructure.premio.PremioEntity;
import cr.ac.una.quiniela.application.infraestructure.premio.PremioRepository;
import cr.ac.una.quiniela.application.infraestructure.quiniela.QuinielaRepository;
import cr.ac.una.quiniela.domain.useCases.IPremioService;
import cr.ac.una.quiniela.web.dto.PremioDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PremioServiceImpl implements IPremioService {
    private final PremioRepository premioRepository;
    private final QuinielaRepository quinielaRepository;

    @Override
    @Transactional
    public PremioDto crearPremio(PremioDto premioDto) {
        var quiniela = quinielaRepository.findById(premioDto.idQuiniela())
                .orElseThrow(() -> new IllegalArgumentException("Quiniela no encontrada"));

        var premio = new PremioEntity();
        premio.setNombre(premioDto.nombre());
        premio.setDescripcion(premioDto.descripcion());
        premio.setCondiciones(premioDto.condiciones());
        premio.setPosicionRanking(premioDto.posicionRanking());
        premio.setIdQuiniela(quiniela);

        var saved = premioRepository.save(premio);
        return toDto(saved);
    }

    @Override
    public List<PremioDto> obtenerPremiosPorQuiniela(Integer quinielaId) {
        return premioRepository.findByIdQuiniela_Id(quinielaId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public void eliminarPremio(Integer id) {
        premioRepository.deleteById(id);
    }

    private PremioDto toDto(PremioEntity entity) {
        return PremioDto.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .condiciones(entity.getCondiciones())
                .posicionRanking(entity.getPosicionRanking())
                .idQuiniela(entity.getIdQuiniela().getId())
                .build();
    }
}
