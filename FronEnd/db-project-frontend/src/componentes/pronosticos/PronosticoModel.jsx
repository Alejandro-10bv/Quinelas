import { useEffect, useState } from 'react';

const PronosticoModel = ({ partido, inscripciones, onClose, onSave }) => {
    const [golesLocal, setGolesLocal] = useState(0);
    const [golesVisitante, setGolesVisitante] = useState(0);
    const [inscripcion, setInscripcion] = useState({});

    useEffect(() => {
        console.log("Partido recibido:", partido);
        console.log("Inscripciones:", inscripciones);
    }, []);

    const handleSubmit = (e) => {
        e.preventDefault();

        // Validación adicional
        if (isNaN(golesLocal) || isNaN(golesVisitante)) {
            alert("Por favor ingresa valores numéricos válidos");
            return;
        }

        onSave({
            partidoId: partido.id,

            golesLocal: parseInt(golesLocal),
            golesVisitante: parseInt(golesVisitante)
        });
    };

    return (
      <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50 p-4">
        <div className="bg-white rounded-lg shadow-xl w-full max-w-md">
          <div className="flex justify-between items-center border-b p-4">
            <h3 className="text-xl font-bold text-gray-800">
              Realizar Pronóstico
            </h3>
            <button
              onClick={onClose}
              className="text-gray-500 hover:text-gray-700">
              ✕
            </button>
          </div>

          <div className="p-6">
            <div className="mb-6 text-center">
              <p className="text-lg font-semibold">
                {partido.equipoLocal.nombre} vs {partido.equipoVisitante.nombre}
              </p>
              <p className="text-sm text-gray-600 mt-1">
                {new Date(partido.fechaHora).toLocaleDateString()} -{" "}
                {partido.estadio}
              </p>
            </div>

            <form onSubmit={handleSubmit}>
              <div className="flex items-center justify-center space-x-1 mb-2">
                <div className="flex flex-col items-center">
                  <label
                    htmlFor="golesLocal"
                    className="block text-sm font-medium text-gray-700 mb-2">
                    {partido.equipoLocal.nombre}
                  </label>
                  <input
                    id="golesLocal"
                    type="number"
                    min="0"
                    value={golesLocal}
                    onChange={(e) => setGolesLocal(e.target.value)}
                    className="w-20 text-center border rounded py-2 px-3"
                    required
                  />
                </div>

                <span className="text-2xl font-bold">-</span>

                <div className="flex flex-col items-center">
                  <label
                    htmlFor="golesVisitante"
                    className="block text-sm font-medium text-gray-700 mb-2">
                    {partido.equipoVisitante.nombre}
                  </label>
                  <input
                    id="golesVisitante"
                    type="number"
                    min="0"
                    value={golesVisitante}
                    onChange={(e) => setGolesVisitante(e.target.value)}
                    className="w-20 text-center border rounded py-2 px-3"
                    required
                  />
                </div>
              </div>
              <div className="flex flex-col items-center mb-5">
                <label
                  htmlFor="seleccionDeInscripcion"
                  className="block text-sm font-medium text-gray-700 mb-2">
                  Selección de Inscripción
                </label>
                <select
                  id="seleccionDeInscripcion"
                  value={inscripcion}
                  onChange={(e) => setInscripcion(e.target.value)}
                  className="w-35 text-center border rounded py-2 px-3"
                  required>
                  <option value="">Seleccionar...</option>
                  {inscripciones.map((insc) => (
                    <option key={insc.idInscripcion} value={insc.idInscripcion}>
                      {insc.nombreQuiniela}
                    </option>
                  ))}
                </select>
              </div>

              <div className="flex justify-end space-x-3">
                <button
                  type="button"
                  onClick={onClose}
                  className="px-4 py-2 border border-gray-300 rounded-md text-gray-700 hover:bg-gray-50 transition-colors">
                  Cancelar
                </button>
                <button
                  type="submit"
                  className="px-4 py-2 bg-green-600 text-white rounded-md hover:bg-green-700 transition-colors">
                  Guardar Pronóstico
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    );
};

export default PronosticoModel;

/*

import { useState } from 'react';

const PronosticoModel = ({ partido, onClose, onSave }) => {
    const [golesLocal, setGolesLocal] = useState(0);
    const [golesVisitante, setGolesVisitante] = useState(0);

    const handleSubmit = (e) => {
        e.preventDefault();
        onSave({
            partidoId: partido.id,
            golesLocal: parseInt(golesLocal),
            golesVisitante: parseInt(golesVisitante)
        });
    };

    return (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50 p-4">
            <div className="bg-white rounded-lg shadow-xl w-full max-w-md">
                <div className="flex justify-between items-center border-b p-4">
                    <h3 className="text-xl font-bold text-gray-800">Realizar Pronóstico</h3>
                    <button
                        onClick={onClose}
                        className="text-gray-500 hover:text-gray-700"
                    >
                        ✕
                    </button>
                </div>

                <div className="p-6">
                    <div className="mb-6 text-center">
                        <p className="text-lg font-semibold">
                            {partido.equipoLocal.nombre} vs {partido.equipoVisitante.nombre}
                        </p>
                        <p className="text-sm text-gray-600 mt-1">
                            {new Date(partido.fechaHora).toLocaleDateString()} - {partido.estadio}
                        </p>
                    </div>

                    <form onSubmit={handleSubmit}>
                        <div className="flex items-center justify-center space-x-6 mb-8">
                            <div className="flex flex-col items-center">
                                <label className="block text-sm font-medium text-gray-700 mb-2">
                                    {partido.equipoLocal.nombre}
                                </label>
                                <input
                                    type="number"
                                    min="0"
                                    value={golesLocal}
                                    onChange={(e) => setGolesLocal(e.target.value)}
                                    className="w-20 text-center border rounded py-2 px-3"
                                    required
                                />
                            </div>

                            <span className="text-2xl font-bold">-</span>

                            <div className="flex flex-col items-center">
                                <label className="block text-sm font-medium text-gray-700 mb-2">
                                    {partido.equipoVisitante.nombre}
                                </label>
                                <input
                                    type="number"
                                    min="0"
                                    value={golesVisitante}
                                    onChange={(e) => setGolesVisitante(e.target.value)}
                                    className="w-20 text-center border rounded py-2 px-3"
                                    required
                                />
                            </div>
                        </div>

                        <div className="flex justify-end space-x-3">
                            <button
                                type="button"
                                onClick={onClose}
                                className="px-4 py-2 border border-gray-300 rounded-md text-gray-700 hover:bg-gray-50 transition-colors"
                            >
                                Cancelar
                            </button>
                            <button
                                type="submit"
                                className="px-4 py-2 bg-green-600 text-white rounded-md hover:bg-green-700 transition-colors"
                            >
                                Guardar Pronóstico
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default PronosticoModel;
*/
