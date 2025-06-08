import axiosInstance from "../../utils/axiosInstance";

const api = axiosInstance;

export const handlePrediccionesSubmit = (
  partidoId,
  resultadoLocal,
  resultadoVisita
) => {
  if (!usuarioActual) return;

  const nuevaPrediccion = {
    id: Date.now(),
    idUsuario: usuarioActual.idUsuario,
    partidoId,
    resultadoLocal,
    resultadoVisita,
    puntos: null,
  };
  setPrediccionesUsuario([...prediccionesUsuario, nuevaPrediccion]);
};

export const handleResultUpdate = (partidoId, resultadoLocal, resultadoVisita) => {
  if (!usuarioActual || usuarioActual.username !== "admin") return;

  const updatedPartidos = fechaPartidos.map((partido) => {
    if (partido.id === partidoId) {
      partido.resultadoLocal = resultadoLocal;
      partido.resultadoVisita = resultadoVisita;
      partido.estado = "finalizado";
      return partido;
    }
    return partido;
  });

  setFechaPartidos(updatedPartidos);
};

export const formatFecha = (fechaHora) => {
  const fecha = new Date(fechaHora);
  const opciones = {
    year: "numeric",
    month: "2-digit",
    day: "2-digit",
    hour: "2-digit",
    minute: "2-digit",
  };
  return fecha.toLocaleDateString("es-ES", opciones);
};

export const getEstadoPartido = (fechaHora, golesLocal, golesVisitante) => {
  const fechaPartido = new Date(fechaHora);
  const fechaActual = new Date();

  // Si la fecha del partido es del pasado, está finalizado
  if (fechaPartido < fechaActual) {
    return { texto: "Finalizado", clase: "bg-green-100 text-green-800" };
  }

  // Si la fecha es futura, verificar los goles para determinar el estado
  if (golesLocal === 0 && golesVisitante === 0) {
    return { texto: "Por jugar", clase: "bg-yellow-100 text-yellow-800" };
  } else {
    return { texto: "Finalizado", clase: "bg-green-100 text-green-800" };
  }
};


export const getPartidos = ({ pagination, setPartidos, setTotalPages, setLoading }) => {
  setLoading(true);
  api
    .get("/partidos", {
      params: {
        page: pagination.currentPage,
        size: pagination.currentSize,
      },
    })
    .then((response) => {
      setPartidos(response.data.partidos);
      setTotalPages(
        Math.ceil(response.data.totalMatches / pagination.currentSize)
      );
      setLoading(false);
    })
    .catch((error) => {
      console.error("Error fetching partidos:", error);
    });
};

export const updatePartidoById = (partidoId, golLocal, golVisita) => {
  api
    .get(`/partidos/actualizar/${partidoId}/${golLocal}/${golVisita}`)
    .then(() => {
      window.location.reload()
    })
    .catch((error) => {
      console.error("Error updating partido:", error);
    });
};