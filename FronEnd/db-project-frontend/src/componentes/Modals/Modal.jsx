import { useEffect, useState } from "react";
import PartidosTable from "../Partidos/PartidosTable";
import { getPartidosWithTorneoId } from "./handler";
import Paginacion from "../Paginacion/Paginacion";

const Modal = ({ isOpen, onClose, title, children, size = "md" }) => {
  if (!isOpen) return null;

  const sizeClasses = {
    sm: "max-w-md",
    md: "max-w-lg",
    lg: "max-w-2xl",
    xl: "max-w-fit",
  };

  return (
    <div className="fixed inset-0 z-50 overflow-y-auto">
      {/* Overlay */}
      <div
        className="fixed inset-0 bg-black bg-opacity-50 transition-opacity"
        onClick={onClose}></div>

      {/* Modal */}
      <div className="flex items-center justify-center p-4">
        <div
          className={`relative bg-white rounded-xl shadow-xl transform transition-all w-full ${sizeClasses[size]}`}
          onClick={(e) => e.stopPropagation()}>
          {/* Header */}
          <div className="flex items-center justify-between p-6 border-b border-gray-200">
            <h3 className="text-lg font-semibold text-gray-900">{title}</h3>
            <button
              onClick={onClose}
              className="text-gray-400 hover:text-gray-600 transition-colors">
              <svg
                className="w-6 h-6"
                fill="none"
                stroke="currentColor"
                viewBox="0 0 24 24">
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  strokeWidth={2}
                  d="M6 18L18 6M6 6l12 12"
                />
              </svg>
            </button>
          </div>

          {/* Content */}
          <div className="p-6">{children}</div>
        </div>
      </div>
    </div>
  );
};

export const TorneoModal = ({ modalState, onClose }) => {
  const [partidos, setPartidos] = useState([]);
  const [totalPages, setTotalPages] = useState(0);
  const [pagination, setPagination] = useState({
    currentPage: 0,
    currentSize: 10,
  });
  const { isOpen, torneo } = modalState;

  useEffect(() => {
    // Validar undefined para todos los parametros
    if (!pagination || !setPartidos || !setTotalPages) return;
    if (isOpen && torneo) {
      getPartidosWithTorneoId({ torneoId: torneo.id, setPartidos, pagination, setTotalPages });
    }
  }, [isOpen, torneo, pagination]);

  if (!isOpen || !torneo) return null;

  return (
    <Modal isOpen={isOpen} onClose={onClose} title={torneo.nombre} size="xl">
      <PartidosTable partidos={partidos} isDescriptive={true} />
      <Paginacion pagination={pagination} setPagination={setPagination} totalPages={totalPages} />
    </Modal>
  );
};

export const QuinielaModal = ({ modalState, onClose }) => {
  const { isOpen, quiniela } = modalState;

  console.log("quiniela modal data:", quiniela);

  if (!isOpen || !quiniela) return null;

  const formatDate = (fecha) => {
    if (!fecha) return "Fecha inválida";

    const date = new Date(fecha);
    return isNaN(date.getTime())
      ? "Fecha inválida"
      : new Intl.DateTimeFormat("es-CR", {
          dateStyle: "medium",
          timeStyle: "short",
        }).format(date);
  };

  return (
    <Modal isOpen={isOpen} onClose={onClose} title={quiniela.nombre} size="xl">
      {/* Descripción general */}
      <div className="mb-4">
        <p className="text-gray-700">{quiniela.descripcion}</p>
        <p className="text-sm text-gray-500 mt-1">
          {quiniela.esPublica ? "🔓 Pública" : "🔒 Privada"} •{" "}
          Abre: {formatDate(quiniela.fechaInicio)} •{" "}
          Cierra: {formatDate(quiniela.fechaCierre)} •{" "}
          Estado: {quiniela.estado}
        </p>
      </div>

      {/* Info del torneo */}
      {quiniela.torneo ? (
        <div className="mb-6">
          <h3 className="font-semibold mb-2">Torneo asociado</h3>
          <div className="border rounded p-3">
            <p className="font-medium text-lg">{quiniela.torneo.nombre}</p>
            <button
              className="mt-2 text-blue-600 hover:underline"
              onClick={() => {
                // Aquí deberia disparar un estado que abra TorneoModal
                console.log("Abrir TorneoModal para:", quiniela.torneo.id);
              }}
            >
              Ver partidos del torneo
            </button>
          </div>
        </div>
      ) : (
        <p className="text-sm text-gray-500">No hay torneo asociado.</p>
      )}

      {/* Acciones */}
      <div className="flex justify-end gap-2 mt-6">
        <button onClick={onClose} className="px-4 py-2 rounded border text-gray-700">
          Cerrar
        </button>
        <button
          onClick={() => {
            // Reemplazar esta lógica con navegación real o cambio de estado
            console.log("Ir a pronosticar partidos de la quiniela:", quiniela.id);
          }}
          className="px-4 py-2 rounded bg-blue-600 text-white hover:bg-blue-700"
        >
          Pronosticar partidos
        </button>
      </div>
    </Modal>
  );
};
