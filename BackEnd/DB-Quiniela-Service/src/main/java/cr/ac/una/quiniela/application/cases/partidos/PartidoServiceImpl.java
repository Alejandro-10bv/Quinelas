package cr.ac.una.quiniela.application.cases.partidos;

import cr.ac.una.quiniela.application.infraestructure.partidos.EquipoEntity;
import cr.ac.una.quiniela.application.infraestructure.partidos.PartidoEntity;
import cr.ac.una.quiniela.application.infraestructure.partidos.PartidoRepository;
import cr.ac.una.quiniela.domain.entities.partidos.Equipo;
import cr.ac.una.quiniela.domain.entities.partidos.Partido;
import cr.ac.una.quiniela.domain.useCases.IPartidosService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PartidoServiceImpl implements IPartidosService {
    private final PartidoRepository partidosRepository;
    private static final ZoneId zoneId = ZoneId.of("America/Costa_Rica");

    @Override
    public Page<Partido> getAllPartidos(Pageable pageable) {
        List<PartidoEntity> partidos = partidosRepository.findAll(pageable).getContent();

        return new PageImpl<>(partidos.stream().map(PartidoMapper::toPartidoDomain).toList());
    }

    @Override
    public Page<Partido> getPartidosByTorneoId(Integer torneoId, Pageable pageable) {
        List<PartidoEntity> partidos = partidosRepository.findByIdTorneo(torneoId,
                pageable).getContent();

        return new PageImpl<>(partidos.stream().map(PartidoMapper::toPartidoDomain).toList());
    }

    @Override
    public Integer getTotalPartidosByTorneoId(Integer torneoId) {
        return Math.toIntExact(partidosRepository.countByIdTorneo(torneoId));
    }

    @Override
    public Integer getTotalPartidos(Pageable pageable) {
        return Math.toIntExact(partidosRepository.count());
    }

    @Override
    public List<Partido> getAllPartidoByTorneoId(Integer torneoId) {
        return partidosRepository.findAllByIdTorneo(torneoId).stream().map(PartidoMapper::toPartidoDomain).toList();
    }

    @Override
    public void updatePartidoById(Integer idPartido, Integer golVisita, Integer golLocal){
        Optional<PartidoEntity>  partidosOpt = partidosRepository.findById(idPartido);
        if(partidosOpt.isPresent()){
            PartidoEntity partido = partidosOpt.get();
            partido.setGolesLocal(golLocal);
            partido.setGolesVisita(golVisita);
            partidosRepository.save(partido);
        }
    }

    private static class PartidoMapper {
        public static Partido toPartidoDomain(PartidoEntity partidoEntity) {
            return Partido.builder()
                    .equipoLocal(EquipoMapper.toEquipoDomain(partidoEntity.getIdEquipoLocal()))
                    .equipoVisitante(EquipoMapper.toEquipoDomain(partidoEntity.getIdEquipoVisitante()))
                    .estadio(partidoEntity.getIdEquipoLocal().getEstadioEntity().getNombre())
                    .fechaHora(LocalDateTime.ofInstant(partidoEntity.getFechaHora(), zoneId))
                    .golesLocal(partidoEntity.getGolesLocal() != null ? partidoEntity.getGolesLocal() : 0)
                    .golesVisitante(partidoEntity.getGolesVisita() != null ? partidoEntity.getGolesVisita() : 0)
                    .id(partidoEntity.getId())
                    .build();
        }
    }

    private static class EquipoMapper {
        public static Equipo toEquipoDomain(EquipoEntity equipoEntity) {
            return Equipo.builder()
                    .nombre(equipoEntity.getNombre())
                    .build();
        }
    }

}
