import { formatFecha, getEstadoPartido, updatePartidoById } from "./handler";
import { useState } from "react";


const PartidoRow = ({
                        partido,
                        usuario,
                        onShowPronosticoModal,
                        onUpdatePartido,
                        isDescriptive
                    }) => {
    const estado = getEstadoPartido(
        partido.fechaHora,
        partido.golesLocal,
        partido.golesVisitante
    );
    const [editandoPartidoId, setEditandoPartidoId] = useState(null);
    const [golesLocal, setGolesLocal] = useState(partido.golesLocal);
    const [golesVisita, setGolesVisita] = useState(partido.golesVisitante);

    const handleGuardar = () => {
        onUpdatePartido(partido.id, golesLocal, golesVisita);
        setEditandoPartidoId(null);
    };

    return (
      <tr className="hover:bg-gray-50 transition-colors">
        <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
          {formatFecha(partido.fechaHora)}
        </td>
        <td className="px-6 py-4 whitespace-nowrap">
          <div className="flex items-center space-x-3">
            <div className="text-sm font-medium text-gray-900">
              {partido.equipoLocal.nombre}
            </div>
            <span className="text-gray-500">vs</span>
            <div className="text-sm font-medium text-gray-900">
              {partido.equipoVisitante.nombre}
            </div>
          </div>
        </td>
        <td className="px-6 py-4 whitespace-nowrap text-center">
          <div className="text-lg font-bold text-gray-900">
            <input
              className="w-[40px] text-center border rounded"
              type="number"
              min={0}
              value={golesLocal}
              disabled={editandoPartidoId !== partido.id || isDescriptive}
              onChange={(e) => setGolesLocal(e.target.value)}
            />
            -
            <input
              className="w-[40px] text-center border rounded"
              type="number"
              min={0}
              value={golesVisita}
              disabled={editandoPartidoId !== partido.id || isDescriptive}
              onChange={(e) => setGolesVisita(e.target.value)}
            />
          </div>
        </td>
        <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-600">
          {partido.estadio}
        </td>
        <td className="px-6 py-4 whitespace-nowrap text-center">
          <span
            className={`inline-flex px-2 py-1 text-xs font-semibold rounded-full ${estado.clase}`}>
            {estado.texto}
          </span>
        </td>
        <td
          className="px-6 py-4 whitespace-nowrap text-center"
          hidden={isDescriptive}>
          {usuario?.scope === "ADMINISTRADOR" ? (
            <>
              <button
                onClick={() =>
                  setEditandoPartidoId(
                    editandoPartidoId === partido.id ? null : partido.id
                  )
                }
                className={`${
                  estado.texto === "Por jugar"
                    ? "opacity-50 cursor-not-allowed"
                    : ""
                } 
              bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded-md text-sm font-medium`}
                disabled={estado.texto === "Por jugar"}
                hidden={editandoPartidoId === partido.id}>
                Actualizar
              </button>
              <button
                onClick={() => {
                  setEditandoPartidoId(
                    editandoPartidoId !== partido.id ? partido.id : null
                  );
                  updatePartidoById(editandoPartidoId, golesLocal, golesVisita);
                }}
                className="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded-md text-sm font-medium"
                hidden={editandoPartidoId !== partido.id}>
                Guardar
              </button>
            </>
          ) : (
            <button
              onClick={() => onShowPronosticoModal(partido)}
              className="bg-green-600 hover:bg-green-700 text-white px-4 py-2 rounded-md text-sm font-medium"
              disabled={estado.texto !== "Por jugar"}
              hidden={estado.texto !== "Por jugar"}>
              Pronosticar
            </button>
          )}
        </td>
      </tr>
    );
};

export default PartidoRow;






/*
import { formatFecha, getEstadoPartido, updatePartidoById } from "./handler";
import { useState } from "react";
import PronosticoModal from "./PronosticoModel.jsx";
import axiosInstance from "../../utils/axiosInstance.js"; // Asegúrate de importar axiosInstance

const PartidoRow = ({ partido, usuario, dispatch, isDescriptive }) => {
    const estado = getEstadoPartido(
        partido.fechaHora,
        partido.golesLocal,
        partido.golesVisitante
    );
    const [editandoPartidoId, setEditandoPartidoId] = useState(null);
    const [golesLocal, setGolesLocal] = useState(partido.golesLocal);
    const [golesVisita, setGolesVisita] = useState(partido.golesVisitante);
    const [showPronosticoModal, setShowPronosticoModal] = useState(false);

    const handleSavePronostico = async (pronosticoData) => {
        try {
            const response = await axiosInstance.post('/pronosticos', {
                ...pronosticoData,
                idInscripcion: usuario.idInscripcion || 1, // Usar el ID de inscripción del usuario o un valor por defecto
                idPartido: partido.id,
                usuarioId: usuario.id
            });

            setShowPronosticoModal(false);
            alert("Pronóstico guardado correctamente!");

            // Notificar al componente padre
            if(onPronosticoSaved){
                onPronosticoSaved(response.data);
            }

            // Opcional: Redirigir a la pantalla de Pronósticos
            // navigate('/pronosticos'); // Si usas react-router

        } catch (error) {
            console.error('Error al guardar pronóstico:', error);
        }
    };

    return (
        <>
            <tr className="hover:bg-gray-50 transition-colors">
                <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                    {formatFecha(partido.fechaHora)}
                </td>
                <td className="px-6 py-4 whitespace-nowrap">
                    <div className="flex items-center space-x-3">
                        <div className="text-sm font-medium text-gray-900">
                            {partido.equipoLocal.nombre}
                        </div>
                        <span className="text-gray-500">vs</span>
                        <div className="text-sm font-medium text-gray-900">
                            {partido.equipoVisitante.nombre}
                        </div>
                    </div>
                </td>
                <td className="px-6 py-4 whitespace-nowrap text-center">
                    <div className="text-lg font-bold text-gray-900">
                        <input
                            className="w-[40px] text-center border rounded"
                            type="number"
                            min={0}
                            defaultValue={partido.golesLocal}
                            disabled={editandoPartidoId !== partido.id || isDescriptive}
                            onChange={(e) => setGolesLocal(e.target.value)}
                        />
                        -
                        <input
                            className="w-[40px] text-center border rounded"
                            type="number"
                            min={0}
                            defaultValue={partido.golesVisitante}
                            disabled={editandoPartidoId !== partido.id || isDescriptive}
                            onChange={(e) => setGolesVisita(e.target.value)}
                        />
                    </div>
                </td>
                <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-600">
                    {partido.estadio}
                </td>
                <td className="px-6 py-4 whitespace-nowrap text-center">
          <span className={`inline-flex px-2 py-1 text-xs font-semibold rounded-full ${estado.clase}`}>
            {estado.texto}
          </span>
                </td>
                <td className="px-6 py-4 whitespace-nowrap text-center" hidden={isDescriptive}>
                    {usuario?.scope.localeCompare("ADMINISTRADOR") === 0 ? (
                        <>
                            <button
                                onClick={() => setEditandoPartidoId(
                                    editandoPartidoId === partido.id ? null : partido.id
                                )}
                                className={`${estado.texto === "Por jugar" ? "opacity-50 cursor-not-allowed" : ""} 
                bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded-md text-sm font-medium transition-colors duration-200 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2`}
                                disabled={estado.texto === "Por jugar"}
                                hidden={editandoPartidoId === partido.id || isDescriptive}
                            >
                                Actualizar
                            </button>
                            <button
                                onClick={() => {
                                    setEditandoPartidoId(editandoPartidoId !== partido.id ? partido.id : null);
                                    updatePartidoById(editandoPartidoId, golesLocal, golesVisita);
                                }}
                                className="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded-md text-sm font-medium transition-colors duration-200 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2"
                                hidden={editandoPartidoId !== partido.id || isDescriptive}
                            >
                                Guardar
                            </button>
                        </>
                    ) : (
                        <button
                            onClick={() => setShowPronosticoModal(true)}
                            className="bg-green-600 hover:bg-green-700 text-white px-4 py-2 rounded-md text-sm font-medium transition-colors duration-200 focus:outline-none focus:ring-2 focus:ring-green-500 focus:ring-offset-2"
                        >
                            Pronosticar
                        </button>
                    )}
                </td>
            </tr>

            {showPronosticoModal && (
                <PronosticoModal
                    partido={partido}
                    onClose={() => setShowPronosticoModal(false)}
                    onSave={handleSavePronostico}
                />
            )}
        </>
    );
};

export default PartidoRow;


/!*
import { formatFecha, getEstadoPartido, updatePartidoById } from "./handler";
import { useState } from "react";


const PartidoRow = ({ partido, usuario, dispatch, isDescriptive }) => {
    const estado = getEstadoPartido(
    partido.fechaHora,
    partido.golesLocal,
    partido.golesVisitante
  );
const [editandoPartidoId, setEditandoPartidoId] = useState(null);
const [golesLocal, setGolesLocal] = useState(partido.golesLocal);
const [golesVisita, setGolesVisita] = useState(partido.golesVisitante);

  return (
    <tr className="hover:bg-gray-50 transition-colors">
      <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
        {formatFecha(partido.fechaHora)}
      </td>
      <td className="px-6 py-4 whitespace-nowrap">
        <div className="flex items-center space-x-3">
          <div className="text-sm font-medium text-gray-900">
            {partido.equipoLocal.nombre}
          </div>
          <span className="text-gray-500">vs</span>
          <div className="text-sm font-medium text-gray-900">
            {partido.equipoVisitante.nombre}
          </div>
        </div>
      </td>
      <td className="px-6 py-4 whitespace-nowrap text-center">
        <div className="text-lg font-bold text-gray-900">
          <input className="w-[40px] text-center border rounded" 
          type="number" min={0} defaultValue={partido.golesLocal}
          disabled={editandoPartidoId !== partido.id || isDescriptive} 
          onChange={(e)=> setGolesLocal(e.target.value) }/>
           - 
           <input className="w-[40px] text-center border rounded" 
           type="number" min={0} defaultValue={partido.golesVisitante}
           disabled={editandoPartidoId !== partido.id || isDescriptive} 
          onChange={(e)=> setGolesVisita(e.target.value) }/>
        </div>
      </td>
      <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-600">
        {partido.estadio}
      </td>
      <td className="px-6 py-4 whitespace-nowrap text-center">
        <span
          className={`inline-flex px-2 py-1 text-xs font-semibold rounded-full ${estado.clase}`}>
          {estado.texto}
        </span>
      </td>
      <td className="px-6 py-4 whitespace-nowrap text-center" hidden={isDescriptive}>
        {usuario?.scope.localeCompare("ADMINISTRADOR") === 0 ? (
          <><button
            onClick={() => setEditandoPartidoId(
              editandoPartidoId === partido.id ? null : partido.id
            )}
            className={`${estado.texto === "Por jugar" ? "opacity-50 cursor-not-allowed" : ""} 
            bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded-md text-sm font-medium transition-colors duration-200 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2`}
            disabled={estado.texto === "Por jugar"}
            hidden={editandoPartidoId === partido.id || isDescriptive}>
            Actualizar
          </button>
          <button
            onClick={() => {setEditandoPartidoId(editandoPartidoId !== partido.id ? partido.id : null);
              updatePartidoById(editandoPartidoId, golesLocal, golesVisita)
            }}
            className="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded-md text-sm font-medium transition-colors duration-200 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2"
            hidden={editandoPartidoId !== partido.id || isDescriptive}>
              Guardar
          </button></>
        ) : (
          <button
            onClick={() => dispatch({ type: "PRONOSTICAR", payload: partido })}
            className="bg-green-600 hover:bg-green-700 text-white px-4 py-2 rounded-md text-sm font-medium transition-colors duration-200 focus:outline-none focus:ring-2 focus:ring-green-500 focus:ring-offset-2"
            >
            Pronosticar
          </button>
        )}
      </td>
    </tr>
  );
};

export default PartidoRow;*!/
*/
