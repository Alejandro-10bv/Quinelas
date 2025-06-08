package cr.ac.una.quiniela.application.cases.quiniela;

import cr.ac.una.quiniela.application.infraestructure.quiniela.QuinielaEntity;
import cr.ac.una.quiniela.application.infraestructure.quiniela.QuinielaRepository;
import cr.ac.una.quiniela.application.infraestructure.quiniela.TipoPuntuacionEntity;
import cr.ac.una.quiniela.application.infraestructure.torneos.TorneoEntity;
import cr.ac.una.quiniela.application.infraestructure.torneos.TorneoRepository;
import cr.ac.una.quiniela.domain.entities.quiniela.Quiniela;
import cr.ac.una.quiniela.domain.entities.quiniela.TipoPuntuacion;
import cr.ac.una.quiniela.domain.entities.torneos.Torneo;
import cr.ac.una.quiniela.domain.useCases.IQuinielaService;
import cr.ac.una.quiniela.web.dto.QuinielaDto;
import cr.ac.una.quiniela.web.dto.TipoPuntuacionDto;
import cr.ac.una.quiniela.web.dto.TorneoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuinielaServiceImpl implements IQuinielaService {
    private final QuinielaRepository quinielaRepository;
    private final TorneoRepository torneoRepository;

    @Override
    public QuinielaDto crearQuiniela(QuinielaDto quinielaDto) {
        var torneo = torneoRepository.findById(quinielaDto.idTorneo())
                .orElseThrow(() -> new IllegalArgumentException("Torneo no encontrado"));

        var quiniela = new QuinielaEntity();
        quiniela.setNombre(quinielaDto.nombre());
        quiniela.setDescripcion(quinielaDto.descripcion());
        quiniela.setEsPublica(quinielaDto.esPublica());
        quiniela.setFechaInicio(quinielaDto.fechaInicio());
        quiniela.setFechaCierre(quinielaDto.fechaCierre());
        quiniela.setEstado(quinielaDto.estado());
        quiniela.setIdTorneo(torneo);

        var saved = quinielaRepository.save(quiniela);
        return toDto(saved);
    }

    @Override
    public Page<QuinielaDto> obtenerQuinielasPorTorneo(Integer torneoId, Pageable pageable) {
        return quinielaRepository.findByIdTorneo_Id(torneoId, pageable)
                .map(this::toDto);
    }

    @Override
    public QuinielaDto obtenerQuinielaPorId(Integer id) {
        return quinielaRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Quiniela no encontrada"));
    }

    @Override
    public Quiniela getById(Integer id) {
        QuinielaEntity entity = quinielaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiniela no encontrada")
        );

        return toQuiniela(entity);
    }

    private static final ZoneId zoneId = ZoneId.of("America/Costa_Rica");

    @Override
    public PageImpl<Quiniela> getAllQuinielas(Pageable page) {
        List<QuinielaEntity> quinielas = quinielaRepository.findAll(page).getContent();
        return new PageImpl<>(quinielas.stream().map(QuinielaMapper::toQuinielaDomain).toList());
    }

    @Override
    public Integer getTotalQuinielas() {
        return Math.toIntExact(quinielaRepository.count());
    }

    private static class QuinielaMapper {
        public static Quiniela toQuinielaDomain(QuinielaEntity quinielaEntity) {
            return Quiniela.builder()
                    .id(quinielaEntity.getId())
                    .nombre(quinielaEntity.getNombre())
                    .descripcion(quinielaEntity.getDescripcion())
                    .esPublica(quinielaEntity.getEsPublica())
                    .fechaInicio(quinielaEntity.getFechaInicio())
                    .fechaCierre(quinielaEntity.getFechaCierre())
                    .estado(quinielaEntity.getEstado())
                    .tipoPuntuacion(TipoPuntuacionMapper.toTipoPuntuacionDomain(quinielaEntity.getIdTipoPuntuacion()))
                    .torneoId(TorneoMapper.toTorneoDomain(quinielaEntity.getIdTorneo()))
                    .build();
        }
    }

    public static class TorneoMapper {
        public static Torneo toTorneoDomain(TorneoEntity torneoEntity) {
            return Torneo.builder()
                    .id(torneoEntity.getId())
                    .nombre(torneoEntity.getNombre())
                    .descripcion(torneoEntity.getDescripcion())
                    .build();
        }

        public static TorneoDto toTorneoDto(Torneo torneo) {
            return TorneoDto.builder()
                    .id(torneo.id())
                    .nombre(torneo.nombre())
                    .descripcion(torneo.descripcion())
                    .build();
        }
    }

    public static class TipoPuntuacionMapper {
        public static TipoPuntuacion toTipoPuntuacionDomain(TipoPuntuacionEntity tipoPuntuacionEntity) {
            return TipoPuntuacion.builder()
                    .id(tipoPuntuacionEntity.getId())
                    .nombre(tipoPuntuacionEntity.getNombre())
                    .puntosObtenidos(tipoPuntuacionEntity.getPuntosObtenidos())
                    .build();
        }

        public static TipoPuntuacionDto toTipoPuntuacionDto(TipoPuntuacion tipoPuntuacion) {
            return TipoPuntuacionDto.builder()
                    .id(tipoPuntuacion.id())
                    .nombre(tipoPuntuacion.nombre())
                    .puntosObtenidos(tipoPuntuacion.puntosObtenidos())
                    .build();
        }
    }

    private QuinielaDto toDto(QuinielaEntity entity) {
        return QuinielaDto.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .esPublica(entity.getEsPublica())
                .fechaInicio(entity.getFechaInicio())
                .fechaCierre(entity.getFechaCierre())
                .estado(entity.getEstado())
                .idTipoPuntuacion(entity.getIdTipoPuntuacion().getId())
                .idTorneo(entity.getIdTorneo().getId())
                .build();
    }

    private Quiniela toQuiniela(QuinielaEntity entity) {
        return Quiniela.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .esPublica(entity.getEsPublica())
                .fechaInicio(entity.getFechaInicio())
                .fechaCierre(entity.getFechaCierre())
                .estado(entity.getEstado())
                .tipoPuntuacion(toTipoPuntuacion(entity.getIdTipoPuntuacion()))
                .torneoId(toTorneo(entity.getIdTorneo()))
                .build();
    }

    private Torneo toTorneo(TorneoEntity idTorneo) {
        return Torneo.builder()
                .id(idTorneo.getId())
                .nombre(idTorneo.getNombre())
                .descripcion(idTorneo.getDescripcion())
                .build();
    }

    private TipoPuntuacion toTipoPuntuacion(TipoPuntuacionEntity entity) {
        return TipoPuntuacion.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .puntosObtenidos(entity.getPuntosObtenidos())
                .build();
    }
}
