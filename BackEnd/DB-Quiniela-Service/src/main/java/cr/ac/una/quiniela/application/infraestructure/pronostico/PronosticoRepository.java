package cr.ac.una.quiniela.application.infraestructure.pronostico;

import cr.ac.una.quiniela.application.infraestructure.inscripcion.InscripcionEntity;
import cr.ac.una.quiniela.application.infraestructure.partidos.PartidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PronosticoRepository extends JpaRepository<PronosticoEntity, Integer> {
    List<PronosticoEntity> findByIdInscripcion_Id(Integer inscripcionId);

    Optional<PronosticoEntity> findByIdPartido_IdAndIdInscripcion_Id(Integer partidoId, Integer inscripcionId);
}
