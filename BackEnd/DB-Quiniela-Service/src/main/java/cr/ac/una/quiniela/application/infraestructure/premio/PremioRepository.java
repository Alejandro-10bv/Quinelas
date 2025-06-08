package cr.ac.una.quiniela.application.infraestructure.premio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PremioRepository extends JpaRepository<PremioEntity, Integer> {
    // Método corregido para buscar premios por ID de quiniela
    List<PremioEntity> findByIdQuiniela_Id(Integer idQuiniela);

    // Opcional: Si necesitas paginación
     Page<PremioEntity> findByIdQuiniela_Id(Integer idQuiniela, Pageable pageable);
}
