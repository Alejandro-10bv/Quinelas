package cr.ac.una.quiniela.application.infraestructure.torneos;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TorneoRepository extends JpaRepository<TorneoEntity, Integer> {
}
