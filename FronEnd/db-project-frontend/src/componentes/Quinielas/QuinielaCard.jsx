const QuinielaCard = ({ quiniela, onClick }) => {
  return (
    <div className="bg-white rounded-xl shadow-lg hover:shadow-xl transition-shadow duration-300 p-4 border border-gray-200 hover:border-gray-300">
      <div className="space-y-4">
        {/* Título de la quiniela */}
        <h3 className="text-xl font-bold text-gray-800 line-clamp-2 leading-tight">
          {quiniela?.nombre}
        </h3>

        {/* Descripción de la quiniela */}
        <p className="text-gray-600 text-sm leading-relaxed line-clamp-3">
          {quiniela?.descripcion}
        </p>

        {/* Footer con información adicional */}
        <div className="flex justify-between items-center pt-2 border-t border-gray-100">
          <span className="text-xs text-gray-500 font-medium">
            Torneo: {quiniela?.torneo?.nombre || "Sin Identificar"}
          </span>
          <button className="text-blue-600 hover:text-blue-700 text-sm font-semibold transition-colors duration-200" onClick={onClick}>
            Ver detalles →
          </button>
        </div>
      </div>
    </div>
  );
};
export default QuinielaCard;
