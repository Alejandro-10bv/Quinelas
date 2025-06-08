package cr.ac.una.quiniela.web.controller;

import cr.ac.una.quiniela.domain.entities.partidos.Partido;
import cr.ac.una.quiniela.domain.entities.quiniela.Quiniela;
import cr.ac.una.quiniela.domain.useCases.IInscripcionService;
import cr.ac.una.quiniela.domain.useCases.IPartidosService;
import cr.ac.una.quiniela.domain.useCases.IQuinielaService;
import cr.ac.una.quiniela.web.responses.InscripcionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class InscripcionesController {
    private final IInscripcionService inscripcionService;
    private final IQuinielaService quinielaService;
    private final IPartidosService partidosService;

    @GetMapping("/inscripciones/{usuarioId}/pronosticos")
    public ResponseEntity<List<InscripcionResponse>> getInscripcionesByUsuarioId(@PathVariable UUID usuarioId) {
        List<InscripcionResponse> inscripciones = inscripcionService.getInscripcionesByUsuarioId(usuarioId)
                .stream().map(i -> {
                    Quiniela quiniela = quinielaService.getById(i.idQuiniela());

                    return InscripcionResponse.builder()
                            .idInscripcion(i.id())
                            .nombreQuiniela(quiniela.nombre())
                            .idPartidos(
                                    partidosService.getAllPartidoByTorneoId(quiniela.torneoId().id()).stream().map(Partido::id).toList()
                            )
                            .build();
                }).toList();
        return ResponseEntity.ok(inscripciones);
    }
}
