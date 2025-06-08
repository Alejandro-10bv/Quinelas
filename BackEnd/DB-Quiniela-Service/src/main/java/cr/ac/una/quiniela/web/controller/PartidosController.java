package cr.ac.una.quiniela.web.controller;

import cr.ac.una.quiniela.domain.useCases.IPartidosService;
import cr.ac.una.quiniela.web.dto.PartidoDto;
import cr.ac.una.quiniela.web.responses.PartidosTableResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PartidosController {
    private final IPartidosService partidosService;

    @GetMapping("/partidos")
    public ResponseEntity<PartidosTableResponse> getPartidos(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        List<PartidoDto> partidos = partidosService.getAllPartidos(pageable).stream()
                .map(p -> PartidoDto.builder()
                        .id(p.id())
                        .fechaHora(p.fechaHora())
                        .equipoLocal(p.equipoLocal())
                        .equipoVisitante(p.equipoVisitante())
                        .golesLocal(p.golesLocal())
                        .golesVisitante(p.golesVisitante())
                        .estadio(p.estadio())
                        .build())
                .toList();

        Integer totalPartidos = partidosService.getTotalPartidos(pageable);

        return ResponseEntity.ok(PartidosTableResponse.builder()
                .totalMatches(totalPartidos)
                .partidos(partidos)
                .build());
    }

    @GetMapping("/partidos/torneo/{idTorneo}")
    public ResponseEntity<PartidosTableResponse> getPartidosByTorneoId(@PageableDefault(size = 10, page = 0) Pageable pageable, @PathVariable Integer idTorneo) {
        List<PartidoDto> partidos = partidosService.getPartidosByTorneoId(idTorneo, pageable).stream()
                .map(p -> PartidoDto.builder()
                        .fechaHora(p.fechaHora())
                        .equipoLocal(p.equipoLocal())
                        .equipoVisitante(p.equipoVisitante())
                        .golesLocal(p.golesLocal())
                        .golesVisitante(p.golesVisitante())
                        .estadio(p.estadio())
                        .build())
                .toList();

        Integer totalPartidos = partidosService.getTotalPartidosByTorneoId(idTorneo);

        return ResponseEntity.ok(PartidosTableResponse.builder()
                .totalMatches(totalPartidos)
                .partidos(partidos)
                .build());
    }

    @GetMapping("/partidos/actualizar/{idPartido}/{golLocal}/{golVisita}")
    public void updatePartidoById(@PathVariable Integer idPartido, @PathVariable Integer golLocal, @PathVariable Integer golVisita) {
        partidosService.updatePartidoById(idPartido, golVisita, golLocal);
    }
}
