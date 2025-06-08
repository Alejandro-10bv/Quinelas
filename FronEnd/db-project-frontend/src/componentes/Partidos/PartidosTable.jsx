import { useEffect, useState } from "react";
import PartidoRow from "./PartidoRow";
import PronosticoModal from "../pronosticos/PronosticoModel.jsx";
import axiosInstance from "../../utils/axiosInstance.js";

const api = axiosInstance;

const PartidosTable = ({
  partidos,
  usuario,
  isDescriptive,
  onUpdatePartido,
}) => {
  const [modalPartido, setModalPartido] = useState(null);
  const [pronosticos, setPronosticos] = useState([]);
  const [inscriptions, setInscriptions] = useState([]);

  useEffect(() => {
    if (!usuario || !usuario.id) {
      return;
    }
    // Obtener pronósticos del usuario
    api
      .get(`/inscripciones/${usuario.id}/pronosticos`)
      .then((response) => {
        setInscriptions(response.data);
        console.log("Inscripciones obtenidas:", response.data);
      })
      .catch((error) => {
        console.error("Error al obtener las inscripciones:", error);
      });
  }, []);

  const handlePronosticoGuardado = (nuevoPronostico) => {
    // Actualizar el estado de pronósticos
    setPronosticos((prev) => [...prev, nuevoPronostico]);
  };

  return (
    <div className="relative">
      <div className="overflow-x-auto">
        <table className="min-w-full divide-y divide-gray-200">
          <thead className="bg-gray-50">
            <tr>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Fecha
              </th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Partido
              </th>
              <th className="px-6 py-3 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">
                Resultado
              </th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Estadio
              </th>
              <th className="px-6 py-3 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">
                Estado
              </th>
              {!isDescriptive && (
                <th className="px-6 py-3 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">
                  Acciones
                </th>
              )}
            </tr>
          </thead>
          <tbody className="bg-white divide-y divide-gray-200">
            {partidos.map((partido) => (
              <PartidoRow
                key={partido.id}
                partido={partido}
                usuario={usuario}
                isDescriptive={isDescriptive}
                onShowPronosticoModal={setModalPartido}
                onUpdatePartido={onUpdatePartido}
              />
            ))}
          </tbody>
        </table>
      </div>

      {modalPartido && (
        <PronosticoModal
          partido={modalPartido}
          inscripciones={inscriptions.filter((i) =>
            i.idPartidos.includes(modalPartido.id)
          )}
          onClose={() => setModalPartido(null)}
          onSave={(pronosticoData) => {
            handlePronosticoGuardado(pronosticoData);
          }}
        />
      )}
    </div>
  );
};

export default PartidosTable;

/*
import PartidoRow from "./PartidoRow";

const PartidosTable = ({ partidos, usuario, dispatch, isDescriptive = false }) => {

  return (
    <div className="container w-full mx-auto">
      <div>
        <h1 className="text-3xl font-bold text-gray-800 mb-2">Partidos</h1>
      </div>
      {partidos?.length === 0 ? (
        <div className="text-center py-12">
          <p className="text-gray-500 text-lg">No hay partidos disponibles</p>
        </div>
      ) : (
        <div className="container bg-white rounded-lg shadow-2xl overflow-hidden w-full">
          <div className="overflow-x-auto">
            <table className="min-w-full divide-y divide-gray-200">
              <thead className="bg-gray-50">
                <tr>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Fecha y Hora
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Equipos
                  </th>
                  <th className="px-6 py-3 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Resultado
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Estadio
                  </th>
                  <th className="px-6 py-3 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Estado
                  </th>
                  <th className="px-6 py-3 text-center text-xs font-medium text-gray-500 uppercase tracking-wider" hidden={isDescriptive}>
                    Acciones
                  </th>
                </tr>
              </thead>
              <tbody className="bg-white divide-y divide-gray-200">
                {partidos?.map((partido, index) => (
                  <PartidoRow
                    key={index}
                    partido={partido}
                    usuario={usuario}
                    dispatch={dispatch}
                    isDescriptive={isDescriptive}
                  />
                ))}
              </tbody>
            </table>
          </div>
        </div>
      )}
    </div>
  );
};
export default PartidosTable;
*/
