package cr.ac.una.quiniela.application.cases.pronostico;

import cr.ac.una.quiniela.application.infraestructure.inscripcion.InscripcionRepository;
import cr.ac.una.quiniela.application.infraestructure.partidos.PartidoRepository;
import cr.ac.una.quiniela.application.infraestructure.pronostico.PronosticoEntity;
import cr.ac.una.quiniela.application.infraestructure.pronostico.PronosticoRepository;
import cr.ac.una.quiniela.domain.useCases.IPronosticoService;
import cr.ac.una.quiniela.web.dto.PronosticoDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PronosticoServiceImpl implements IPronosticoService {
    private final PronosticoRepository pronosticoRepository;
    private final PartidoRepository partidoRepository;
    private final InscripcionRepository inscripcionRepository;

    @Override
    @Transactional
    public PronosticoDto crearPronostico(PronosticoDto pronosticoDto) {
        var partido = partidoRepository.findById(pronosticoDto.idPartido())
                .orElseThrow(() -> new IllegalArgumentException("Partido no encontrado"));

        var inscripcion = inscripcionRepository.findById(pronosticoDto.idInscripcion())
                .orElseThrow(() -> new IllegalArgumentException("Inscripción no encontrada"));

        var pronostico = new PronosticoEntity();
        pronostico.setIdPartido(partido);
        pronostico.setIdInscripcion(inscripcion);
        pronostico.setGolesLocal(pronosticoDto.golesLocal());
        pronostico.setGolesVisita(pronosticoDto.golesVisita());
        pronostico.setFechaHora(Instant.now());

        var saved = pronosticoRepository.save(pronostico);
        return toDto(saved);
    }

    @Override
    @Transactional
    public PronosticoDto actualizarPronostico(PronosticoDto pronosticoDto) {
        var pronostico = pronosticoRepository.findById(pronosticoDto.id())
                .orElseThrow(() -> new IllegalArgumentException("Pronóstico no encontrado"));

        pronostico.setGolesLocal(pronosticoDto.golesLocal());
        pronostico.setGolesVisita(pronosticoDto.golesVisita());
        pronostico.setFechaHora(Instant.now());

        var updated = pronosticoRepository.save(pronostico);
        return toDto(updated);
    }

    @Override
    public List<PronosticoDto> obtenerPronosticosPorInscripcion(Integer idInscripcion) {
        return pronosticoRepository.findByIdInscripcion_Id(idInscripcion).stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public PronosticoDto obtenerPronosticoPorPartidoYInscripcion(Integer idPartido, Integer idInscripcion) {
        return pronosticoRepository.findByIdPartido_IdAndIdInscripcion_Id(idPartido, idInscripcion)
                .map(this::toDto)
                .orElse(null);
    }

    @Override
    @Transactional
    public void eliminarPronostico(Integer id) {
        pronosticoRepository.deleteById(id);
    }

    private PronosticoDto toDto(PronosticoEntity entity) {
        return PronosticoDto.builder()
                .id(entity.getId())
                .idPartido(entity.getIdPartido().getId())
                .idInscripcion(entity.getIdInscripcion().getId())
                .golesLocal(entity.getGolesLocal())
                .golesVisita(entity.getGolesVisita())
                .fechaHora(entity.getFechaHora())
                .build();
    }
}
