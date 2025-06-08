package cr.ac.una.quiniela.application.infraestructure.inscripcion;

import cr.ac.una.quiniela.application.infraestructure.quiniela.QuinielaEntity;
import cr.ac.una.quiniela.application.infraestructure.owners.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface InscripcionRepository extends JpaRepository<InscripcionEntity, Integer> {
    boolean existsByIdUsuario_IdAndIdQuiniela_Id(UUID usuarioId, Integer quinielaId);
    Optional<InscripcionEntity> findByIdUsuario_IdAndIdQuiniela_Id(UUID usuarioId, Integer quinielaId);

    @Query("SELECT i FROM InscripcionEntity i WHERE i.idUsuario.id = :usuarioId")
    List<InscripcionEntity> findAllByIdUsuario(@Param("usuarioId") UUID usuarioId);
}
