package cr.ac.una.quiniela.application.infraestructure.partidos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartidoRepository extends JpaRepository<PartidoEntity, Integer> {
    Page<PartidoEntity> findByIdTorneo(Integer idTorneo, Pageable pageable);
    Long countByIdTorneo(Integer torneoId);
    List<PartidoEntity> findAllByIdTorneo(Integer idTorneo);
}
