package cr.ac.una.quiniela.domain.useCases;

import cr.ac.una.quiniela.domain.entities.quiniela.Inscripcion;
import cr.ac.una.quiniela.web.dto.InscripcionDto;

import java.util.List;
import java.util.UUID;

public interface IInscripcionService {
    InscripcionDto crearInscripcion(InscripcionDto inscripcionDto);
    boolean existeInscripcion(UUID usuarioId, Integer quinielaId);
    InscripcionDto obtenerInscripcion(UUID usuarioId, Integer quinielaId); // Asegúrate de agregar este método
    List<Inscripcion> getInscripcionesByUsuarioId(UUID usuarioId);
}
