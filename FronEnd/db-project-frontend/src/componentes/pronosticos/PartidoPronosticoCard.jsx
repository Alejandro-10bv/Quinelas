const PartidoPronosticoCard = ({ partido, pronostico, onChange, onSave }) => {
    const [golesLocal, setGolesLocal] = useState(pronostico?.golesLocal || 0);
    const [golesVisita, setGolesVisita] = useState(pronostico?.golesVisitante || 0);

    const handleGuardar = () => {
        onChange(partido.id, "golesLocal", golesLocal);
        onChange(partido.id, "golesVisitante", golesVisita);
        onSave(partido.id);
    };

    return (
        <div className="bg-white rounded-xl shadow-md overflow-hidden border border-gray-200 hover:shadow-lg transition-shadow">
            <div className="p-6">
                <div className="flex justify-between items-center mb-4">
          <span className="text-sm text-gray-500">
            {new Date(partido.fechaHora).toLocaleDateString()} - {partido.torneo.nombre}
          </span>
                    <span className="px-2 py-1 text-xs font-semibold bg-blue-100 text-blue-800 rounded-full">
            {partido.estadio}
          </span>
                </div>

                <div className="flex items-center justify-between mb-6">
                    <div className="text-center">
                        <div className="font-bold text-lg">{partido.equipoLocal.nombre}</div>
                        <img
                            src={partido.equipoLocal.escudo}
                            alt={`Escudo ${partido.equipoLocal.nombre}`}
                            className="h-16 mx-auto my-2"
                        />
                        <input
                            id={`golesLocal-${partido.id}`}
                            name={`golesLocal-${partido.id}`}
                            type="number"
                            min="0"
                            value={golesLocal}
                            onChange={(e) => setGolesLocal(e.target.value)}
                            className="w-16 text-center border rounded py-1"
                        />
                    </div>

                    <div className="text-2xl font-bold mx-4">VS</div>

                    <div className="text-center">
                        <div className="font-bold text-lg">{partido.equipoVisitante.nombre}</div>
                        <img
                            src={partido.equipoVisitante.escudo}
                            alt={`Escudo ${partido.equipoVisitante.nombre}`}
                            className="h-16 mx-auto my-2"
                        />
                        <input
                            id={`golesVisitante-${partido.id}`}
                            name={`golesVisitante-${partido.id}`}
                            type="number"
                            min="0"
                            value={golesVisita}
                            onChange={(e) => setGolesVisita(e.target.value)}
                            className="w-16 text-center border rounded py-1"
                        />
                    </div>
                </div>

                <button
                    onClick={handleGuardar}
                    className="w-full bg-blue-600 hover:bg-blue-700 text-white py-2 px-4 rounded-md transition-colors"
                >
                    {pronostico ? "Actualizar Pronóstico" : "Guardar Pronóstico"}
                </button>
            </div>
        </div>
    );
};

export default PartidoPronosticoCard;