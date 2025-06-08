package cr.ac.una.quiniela.web.controller;

import cr.ac.una.quiniela.application.cases.quiniela.QuinielaServiceImpl;
import cr.ac.una.quiniela.web.dto.QuinielaDto;
import cr.ac.una.quiniela.web.responses.QuinielasViewResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
public class QuinielasController {

    private final QuinielaServiceImpl quinielasServiceImpl;

    public QuinielasController(QuinielaServiceImpl quinielasServiceImpl) {
        this.quinielasServiceImpl = quinielasServiceImpl;
    }

    @GetMapping("/quinielas")
    public ResponseEntity<QuinielasViewResponse> getQuinielas(@PageableDefault(size = 9) Pageable pageable) {
        List<QuinielaDto> quinielas = quinielasServiceImpl.getAllQuinielas(pageable).map(
                quiniela -> QuinielaDto.builder()
                        .id(quiniela.id())
                        .nombre(quiniela.nombre())
                        .descripcion(quiniela.descripcion())
                        .esPublica(quiniela.esPublica())
                        .fechaInicio(quiniela.fechaInicio())
                        .fechaCierre(quiniela.fechaCierre())
                        .estado(quiniela.estado())
                        .idTipoPuntuacion(quiniela.tipoPuntuacion().id())
                        .idTorneo(quiniela.torneoId().id())
                        .build()).toList();

        Integer totalQuinielas = quinielasServiceImpl.getTotalQuinielas();
        return ResponseEntity.ok(
                QuinielasViewResponse.builder()
                        .totalQuinielas(totalQuinielas)
                        .quinielas(quinielas)
                        .build());
    }
}
