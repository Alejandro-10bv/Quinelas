const Paginacion = ({ pagination, setPagination, totalPages }) => {
    return (
      <div className="flex justify-between items-center p-6">
        <div className="flex items-center space-x-4">
          <button
            onClick={() =>
              setPagination((prev) => ({
                ...prev,
                currentPage: prev.currentPage - 1,
              }))
            }
            className={`px-4 py-2 bg-blue-600 text-white rounded-md shadow-sm disabled:opacity-50 disabled:cursor-not-allowed hover:bg-blue-700 transition duration-200 ${
              pagination.currentPage === 0 ? "disabled" : ""
            }`}
            disabled={pagination.currentPage === 0 || totalPages === 0}>
            Anterior
          </button>
          <span className="text-sm text-gray-600">
            Página {pagination.currentPage + 1} de {totalPages}
          </span>
          <button
            onClick={() =>
              setPagination((prev) => ({
                ...prev,
                currentPage: prev.currentPage + 1,
              }))
            }
            className={`px-4 py-2 bg-blue-600 text-white rounded-md shadow-sm disabled:opacity-50 disabled:cursor-not-allowed hover:bg-blue-700 transition duration-200 ${
              pagination.currentPage === totalPages - 1 ? "disabled" : ""
            }`}
            disabled={pagination.currentPage === totalPages - 1 || totalPages === 0}>
            Siguiente
          </button>
        </div>
      </div>
    );
}

export default Paginacion;