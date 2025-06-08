package cr.ac.una.quiniela.domain.useCases;

import cr.ac.una.quiniela.domain.entities.quiniela.Quiniela;
import cr.ac.una.quiniela.web.dto.QuinielaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IQuinielaService {
    QuinielaDto crearQuiniela(QuinielaDto quinielaDto);
    Page<QuinielaDto> obtenerQuinielasPorTorneo(Integer torneoId, Pageable pageable);
    QuinielaDto obtenerQuinielaPorId(Integer id);
    Quiniela getById(Integer id);
    Page<Quiniela> getAllQuinielas(Pageable page);
    Integer getTotalQuinielas();
}
