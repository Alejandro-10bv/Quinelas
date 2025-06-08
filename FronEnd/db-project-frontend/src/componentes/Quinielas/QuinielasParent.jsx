import { useEffect, useState } from "react";
import QuinielaCard from "./QuinielaCard";
import Paginacion from "../Paginacion/Paginacion";
import { getQuinielas } from "./handler";
import { QuinielaModal } from "../Modals/Modal";

const QuinielasParent = ({ usuario }) => {
  const [quinielas, setQuinielas] = useState([]);
  const [loading, setLoading] = useState(true);
  const [totalPages, setTotalPages] = useState(0);
  const [pagination, setPagination] = useState({
    currentPage: 0,
    currentSize: 10,
  });
  const [modal, setModal] = useState({
    isOpen: false,
    quiniela: null,
  });

  useEffect(() => {
    getQuinielas({
      setQuinielas,
      setTotalPages,
      setLoading,
      pagination,
    });
  }, []);

  const handleQuinielaClick = (quiniela) => {
    setModal({ isOpen: true, quiniela });
  };

  const handleCloseModal = () => {
    setModal({ isOpen: false, quiniela: null });
  };

  if (loading) {
    return (
      <div className="flex justify-center items-center h-64">
        <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-600"></div>
        <span className="ml-3 text-gray-600">Cargando quinielas...</span>
      </div>
    );
  }

  return (
    <>
      {!quinielas || quinielas.length === 0 ? (
        <div className="min-h-96 flex items-center justify-center">
          <div className="text-center space-y-4">
            <div className="w-16 h-16 mx-auto bg-gray-100 rounded-full flex items-center justify-center">
              <svg
                className="w-8 h-8 text-gray-400"
                fill="none"
                stroke="currentColor"
                viewBox="0 0 24 24">
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  strokeWidth={2}
                  d="M19 11H5m14 0a2 2 0 012 2v6a2 2 0 01-2 2H5a2 2 0 01-2-2v-6a2 2 0 012-2m14 0V9a2 2 0 00-2-2M5 11V9a2 2 0 012-2m0 0V5a2 2 0 012-2h6a2 2 0 012 2v2M7 7h10"
                />
              </svg>
            </div>
            <h3 className="text-lg font-semibold text-gray-600">
              No hay quinielas disponibles
            </h3>
            <p className="text-gray-500">
              Los quinielas aparecerán aquí cuando estén disponibles.
            </p>
          </div>
        </div>
      ) : (
        <div className="w-full space-y-6">
          {/* Header con información */}
          <div className="flex justify-between items-center">
            <h2 className="text-2xl font-bold text-gray-800">
              Quinielas Disponibles
            </h2>
            <span className="text-sm text-gray-500 bg-gray-100 px-3 py-1 rounded-full">
              {quinielas?.length} Quiniela{quinielas?.length !== 1 ? "s" : ""} en total
            </span>
          </div>

          {/* Grid de quinielas */}
          <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6 auto-rows-fr">
            {quinielas.map((quiniela, index) => (
              <QuinielaCard
                key={quiniela.id || index}
                quiniela={quiniela}
                onClick={() => handleQuinielaClick(quiniela)}
              />
            ))}
          </div>
        </div>
      )}
      {totalPages > 0 && (
        <Paginacion
          pagination={pagination}
          setPagination={setPagination}
          totalPages={totalPages}
        />
      )}
      {/* Modal para mostrar detalles de las Quinielas */}
      {modal && <QuinielaModal modalState={modal} onClose={handleCloseModal} />}
    </>
  );
};

export default QuinielasParent;
