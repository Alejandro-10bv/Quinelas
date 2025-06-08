package cr.ac.una.quiniela.web.controller;


import cr.ac.una.quiniela.domain.useCases.IPronosticoService;
import cr.ac.una.quiniela.web.dto.PronosticoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/pronosticos")
@RequiredArgsConstructor
public class PronosticoController {

    private final IPronosticoService pronosticoService;

    @PostMapping
    public ResponseEntity<?> crearPronostico(@RequestBody PronosticoDto pronosticoDto) {
        try {
            // Validación manual
            if (pronosticoDto.idPartido() == null || pronosticoDto.idInscripcion() == null) {
                return ResponseEntity.badRequest().body("ID de partido e inscripción son requeridos");
            }
            if (pronosticoDto.golesLocal() == null || pronosticoDto.golesVisita() == null) {
                return ResponseEntity.badRequest().body("Los goles local y visita son requeridos");
            }

            PronosticoDto nuevoPronostico = pronosticoService.crearPronostico(
                    pronosticoDto.withFechaHora(Instant.now())
            );
            return ResponseEntity.ok(nuevoPronostico);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPronostico(
            @PathVariable Integer id,
            @RequestBody PronosticoDto pronosticoDto) {
        try {
            // Validación manual
            if (!Objects.equals(id, pronosticoDto.id())) {
                return ResponseEntity.badRequest().body("ID del path no coincide con el cuerpo");
            }
            if (pronosticoDto.golesLocal() == null || pronosticoDto.golesVisita() == null) {
                return ResponseEntity.badRequest().body("Los goles local y visita son requeridos");
            }

            PronosticoDto actualizado = pronosticoService.actualizarPronostico(
                    pronosticoDto.withFechaHora(Instant.now())
            );
            return ResponseEntity.ok(actualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/inscripcion/{idInscripcion}")
    public ResponseEntity<List<PronosticoDto>> obtenerPronosticosPorInscripcion(
            @PathVariable Integer idInscripcion) {
        List<PronosticoDto> pronosticos = pronosticoService.obtenerPronosticosPorInscripcion(idInscripcion);
        return ResponseEntity.ok(pronosticos);
    }

    @GetMapping("/partido/{idPartido}/inscripcion/{idInscripcion}")
    public ResponseEntity<?> obtenerPronosticoPorPartidoYInscripcion(
            @PathVariable Integer idPartido,
            @PathVariable Integer idInscripcion) {
        try {
            PronosticoDto pronostico = pronosticoService.obtenerPronosticoPorPartidoYInscripcion(idPartido, idInscripcion);
            return ResponseEntity.ok(pronostico);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPronostico(@PathVariable Integer id) {
        try {
            pronosticoService.eliminarPronostico(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
