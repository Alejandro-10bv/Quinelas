package cr.ac.una.quiniela.application.infraestructure.view;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Nationalized;

/**
 * Mapping for DB view
 */
@Getter
@Setter
@Entity
@Immutable
@Table(name = "vw_ranking_quiniela")
public class VwRankingQuiniela {

    @Id
    @Column(name = "ID_quiniela", nullable = false)
    private Integer idQuiniela;

    @Nationalized
    @Column(name = "nombre_completo", length = 152)
    private String nombreCompleto;

    @Column(name = "puntos_totales", nullable = false)
    private Integer puntosTotales;

}
