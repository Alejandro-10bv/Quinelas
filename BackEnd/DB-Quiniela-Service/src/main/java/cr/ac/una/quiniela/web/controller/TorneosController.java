package cr.ac.una.quiniela.web.controller;

import cr.ac.una.quiniela.application.cases.torneos.TorneosServiceImpl;
import cr.ac.una.quiniela.web.dto.TorneoDto;
import cr.ac.una.quiniela.web.responses.TorneosViewResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
public class TorneosController {

    private final TorneosServiceImpl torneosServiceImpl;

    public TorneosController(TorneosServiceImpl torneosServiceImpl) {
        this.torneosServiceImpl = torneosServiceImpl;
    }

    @GetMapping("/torneos")
    public ResponseEntity<TorneosViewResponse> getTorneos(@PageableDefault(size = 9) Pageable pageable) {
        List<TorneoDto> torneos = torneosServiceImpl.getAllTorneos(pageable).map(
                torneo -> TorneoDto.builder()
                        .id(torneo.id())
                        .nombre(torneo.nombre())
                        .descripcion(torneo.descripcion())
                        .build()).toList();

        Integer totalTorneos = torneosServiceImpl.getTotalTorneos();
        return ResponseEntity.ok(
                TorneosViewResponse.builder()
                        .torneos(torneos)
                        .totalTorneos(totalTorneos)
                        .build());
    }
}
