package cr.ac.una.quiniela.application.cases.inscripcion;

import cr.ac.una.quiniela.application.infraestructure.inscripcion.InscripcionEntity;
import cr.ac.una.quiniela.application.infraestructure.inscripcion.InscripcionRepository;
import cr.ac.una.quiniela.application.infraestructure.owners.UsuarioRepository;
import cr.ac.una.quiniela.application.infraestructure.quiniela.QuinielaRepository;
import cr.ac.una.quiniela.domain.entities.quiniela.Inscripcion;
import cr.ac.una.quiniela.domain.useCases.IInscripcionService;
import cr.ac.una.quiniela.web.dto.InscripcionDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InscripcionServiceImpl implements IInscripcionService {
    private final InscripcionRepository inscripcionRepository;
    private final UsuarioRepository usuarioRepository;
    private final QuinielaRepository quinielaRepository;

    @Override
    @Transactional
    public InscripcionDto crearInscripcion(InscripcionDto inscripcionDto) {
        var usuario = usuarioRepository.findById(inscripcionDto.usuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        var quiniela = quinielaRepository.findById(inscripcionDto.quinielaId())
                .orElseThrow(() -> new IllegalArgumentException("Quiniela no encontrada"));

        var inscripcion = new InscripcionEntity();
        inscripcion.setIdUsuario(usuario);
        inscripcion.setIdQuiniela(quiniela);
        inscripcion.setCumpleCondiciones(inscripcionDto.cumpleCondiciones());
        inscripcion.setAceptaReglas(inscripcionDto.aceptaReglas());

        var saved = inscripcionRepository.save(inscripcion);
        return toDto(saved);
    }

    @Override
    public boolean existeInscripcion(UUID usuarioId, Integer quinielaId) {
        return inscripcionRepository.existsByIdUsuario_IdAndIdQuiniela_Id(usuarioId, quinielaId);
    }


    @Override
    public InscripcionDto obtenerInscripcion(UUID usuarioId, Integer quinielaId) {
        return inscripcionRepository.findByIdUsuario_IdAndIdQuiniela_Id(usuarioId, quinielaId)
                .map(this::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Inscripción no encontrada"));
    }

    @Override
    public List<Inscripcion> getInscripcionesByUsuarioId(UUID usuarioId) {
        List<InscripcionEntity> inscripciones = inscripcionRepository.findAllByIdUsuario(usuarioId);

        return inscripciones.stream().map(this::toInscripcion).toList();
    }

    private InscripcionDto toDto(InscripcionEntity entity) {
        return InscripcionDto.builder()
                .id(entity.getId())
                .usuarioId(entity.getIdUsuario().getId())
                .quinielaId(entity.getIdQuiniela().getId())
                .cumpleCondiciones(entity.getCumpleCondiciones())
                .aceptaReglas(entity.getAceptaReglas())
                .build();
    }

    private Inscripcion toInscripcion(InscripcionEntity entity) {
        return Inscripcion.builder()
                .id(entity.getId())
                .idUsuario(entity.getIdUsuario().getId())
                .idQuiniela(entity.getIdQuiniela().getId())
                .cumpleCondiciones(entity.getCumpleCondiciones())
                .aceptaReglas(entity.getAceptaReglas())
                .build();

    }
}
