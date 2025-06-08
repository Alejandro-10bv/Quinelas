import { useEffect, useState, useReducer } from "react";
import PartidosTable from "./PartidosTable";
import Paginacion from "../Paginacion/Paginacion";
import { getPartidos } from "./handler";

const reducer = (state, action) => {
  switch (action.type) {
    case "ACTUALIZAR":
      return {
        partido: action.payload,
        is: "actualizando",
      };
    case "PRONOSTICAR":
      return {
        partido: action.payload,
        is: "pronosticando",
      };
    default:
      return {
        partido: null,
        is: null,
      };
  }
};

const PartidosParent = ({ usuario }) => {
  const [state, dispatch] = useReducer(reducer, { partido: null, is: null });
  const [partidos, setPartidos] = useState([]);
  const [loading, setLoading] = useState(true);
  const [totalPages, setTotalPages] = useState(0);
  const [pagination, setPagination] = useState({
    currentPage: 0,
    currentSize: 10,
  });

  const handleNewPronostico = (newPronostico) => {
    console.log('Nuevo pronóstico guardado:', newPronostico);
    alert('Pronóstico guardado correctamente!');

    // Llama a la función de navegación si está disponible
    if (onNavigateToPronosticos) {
      onNavigateToPronosticos();
    }
  };

  useEffect(() => {
    if (!pagination || !setPartidos || !setTotalPages || !setLoading) return;
    getPartidos({ pagination, setPartidos, setTotalPages, setLoading });
  }, [pagination]);


  if (loading) {
    return (
      <div className="flex justify-center items-center h-64">
        <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-600"></div>
        <span className="ml-3 text-gray-600">Cargando partidos...</span>
      </div>
    );
  }

  if(state.is === "actualizando") {
    return (
      <div className="flex justify-center items-center h-64">
        {/* AQUI SE PONE EL COMPONENTE PARA ACTUALIZAR PARTIDOS */}
        <span className="text-gray-600">Actualizando partido...</span>
      </div>
    );
  }

  if(state.is === "pronosticando") {
    return (
      <div className="flex justify-center items-center h-64">
        {/* AQUI SE PONE EL COMPONENTE PARA PRONOSTICAR PARTIDOS */}
        <span className="text-gray-600">Pronosticando partido...</span>
      </div>
    );
  }

  return (
    <div className="container w-full">
      <PartidosTable
        partidos={partidos}
        usuario={usuario}
        dispatch={dispatch}
      />
      {totalPages > 0 && (
        <Paginacion
          pagination={pagination}
          setPagination={setPagination}
          totalPages={totalPages}
        />
      )}
    </div>
  );
};

export default PartidosParent;
