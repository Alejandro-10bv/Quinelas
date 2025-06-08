package cr.ac.una.quiniela.application.infraestructure.quiniela;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPuntuacionRepository extends JpaRepository<TipoPuntuacionEntity, Integer> {
}
