package cr.ac.una.quiniela.application.infraestructure.owners;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

	Optional<Usuario> findByCorreoElectronico(String correo);

}
