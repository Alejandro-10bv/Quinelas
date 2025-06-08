package cr.ac.una.quiniela.application.infraestructure.quiniela;

import cr.ac.una.quiniela.application.infraestructure.torneos.TorneoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuinielaRepository extends JpaRepository<QuinielaEntity, Integer> {

    // Método corregido para búsqueda paginada por ID de torneo
    Page<QuinielaEntity> findByIdTorneo_Id(Integer torneoId, Pageable pageable);

    // Versión alternativa (sin paginación)
    // List<QuinielaEntity> findByIdTorneo_Id(Integer torneoId);

    // Otra alternativa con nombre más corto
    // Page<QuinielaEntity> findByTorneoId(Integer torneoId, Pageable pageable);
}
