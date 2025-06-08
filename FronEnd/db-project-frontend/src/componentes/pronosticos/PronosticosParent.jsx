import { useState, useEffect } from "react";
import axiosInstance from "../../utils/axiosInstance";
import PartidoPronosticoCard from "./PartidoPronosticoCard";

const PronosticosParent = ({ usuario }) => {
    const [partidos, setPartidos] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [pronosticosGuardados, setPronosticosGuardados] = useState({});
    const [guardando, setGuardando] = useState(false);

    // Obtener partidos disponibles para pronóstico
    useEffect(() => {
        const fetchPartidosParaPronostico = async () => {
            try {
                setLoading(true);
                const response = await axiosInstance.get("/partidos/proximos");
                setPartidos(response.data);
                await cargarPronosticosExistentes();
            } catch (err) {
                setError("Error al cargar partidos para pronóstico");
                console.error(err);
            } finally {
                setLoading(false);
            }
        };

        fetchPartidosParaPronostico();
    }, []);

    // Cargar pronósticos existentes del usuario
    const cargarPronosticosExistentes = async () => {
        try {
            const response = await axiosInstance.get(`/pronosticos/usuario/${usuario.id}`);
            const pronosticosMap = {};
            response.data.forEach(p => {
                pronosticosMap[p.partidoId] = {
                    id: p.id,
                    golesLocal: p.golesLocal,
                    golesVisitante: p.golesVisitante,
                    fechaPronostico: p.fechaPronostico
                };
            });
            setPronosticosGuardados(pronosticosMap);
        } catch (err) {
            console.error("Error al cargar pronósticos existentes:", err);
        }
    };

    // Manejar cambio en pronóstico
    const handlePronosticoChange = (partidoId, campo, valor) => {
        const valorNumerico = parseInt(valor) || 0;
        setPronosticosGuardados(prev => ({
            ...prev,
            [partidoId]: {
                ...(prev[partidoId] || {}),
                [campo]: valorNumerico
            }
        }));
    };

    // Guardar pronóstico
    const guardarPronostico = async (partidoId) => {
        setGuardando(true);
        try {
            const pronostico = pronosticosGuardados[partidoId];

            if (!pronostico) {
                throw new Error("Debes completar el pronóstico primero");
            }

            const payload = {
                partidoId,
                golesLocal: pronostico.golesLocal,
                golesVisitante: pronostico.golesVisitante,
                usuarioId: usuario.id
            };

            // Si ya existe un ID, hacemos PUT en lugar de POST
            const url = pronostico.id
                ? `/pronosticos/${pronostico.id}`
                : "/pronosticos";

            const method = pronostico.id ? 'put' : 'post';

            await axiosInstance[method](url, payload);

            // Recargar los pronósticos para asegurar consistencia
            await cargarPronosticosExistentes();

            alert("Pronóstico guardado correctamente");

        } catch (err) {
            console.error("Error al guardar pronóstico:", err);
            const errorMessage = err.response?.data?.message
                || err.message
                || "Error al guardar el pronóstico";
            alert(errorMessage);
        } finally {
            setGuardando(false);
        }
    };

    if (loading) {
        return (
            <div className="flex justify-center items-center h-64">
                <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-600"></div>
                <span className="ml-3 text-gray-600">Cargando partidos...</span>
            </div>
        );
    }

    if (error) {
        return (
            <div className="text-center py-12">
                <p className="text-red-500 text-lg">{error}</p>
                <button
                    onClick={() => window.location.reload()}
                    className="mt-4 px-4 py-2 bg-blue-500 text-white rounded"
                >
                    Reintentar
                </button>
            </div>
        );
    }

    return (
        <div className="container mx-auto px-4 py-8">
            <div className="mb-8">
                <h1 className="text-3xl font-bold text-gray-800 mb-2">Mis Pronósticos</h1>
                <p className="text-gray-600">
                    Realiza tus pronósticos para los próximos partidos
                </p>
                {guardando && (
                    <div className="mt-2 text-blue-600">
                        Guardando cambios...
                    </div>
                )}
            </div>

            {partidos.length === 0 ? (
                <div className="text-center py-12">
                    <p className="text-gray-500 text-lg">
                        No hay partidos disponibles para pronosticar
                    </p>
                </div>
            ) : (
                <div className="grid gap-6 md:grid-cols-2 lg:grid-cols-3">
                    {partidos.map(partido => (
                        <PartidoPronosticoCard
                            key={partido.id}
                            partido={partido}
                            pronostico={pronosticosGuardados[partido.id]}
                            onChange={handlePronosticoChange}
                            onSave={guardarPronostico}
                            disabled={guardando}
                        />
                    ))}
                </div>
            )}
        </div>
    );
};

export default PronosticosParent;





/*import { useState, useEffect } from "react";
import axiosInstance from "../../utils/axiosInstance";
import PartidoPronosticoCard from "./PartidoPronosticoCard";

const PronosticosParent = ({ usuario }) => {
    const [partidos, setPartidos] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [pronosticosGuardados, setPronosticosGuardados] = useState({});

    // Obtener partidos disponibles para pronóstico
    useEffect(() => {
        const fetchPartidosParaPronostico = async () => {
            try {
                setLoading(true);
                const response = await axiosInstance.get("/partidos/proximos");
                setPartidos(response.data);
                cargarPronosticosExistentes();
            } catch (err) {
                setError("Error al cargar partidos para pronóstico");
                console.error(err);
            } finally {
                setLoading(false);
            }
        };

        fetchPartidosParaPronostico();
    }, []);

    // Cargar pronósticos existentes del usuario
    const cargarPronosticosExistentes = async () => {
        try {
            const response = await axiosInstance.get(`/pronosticos/usuario/${usuario.id}`);
            const pronosticosMap = {};
            response.data.forEach(p => {
                pronosticosMap[p.partidoId] = {
                    golesLocal: p.golesLocal,
                    golesVisitante: p.golesVisitante
                };
            });
            setPronosticosGuardados(pronosticosMap);
        } catch (err) {
            console.error("Error al cargar pronósticos existentes:", err);
        }
    };

    // Manejar cambio en pronóstico
    const handlePronosticoChange = (partidoId, campo, valor) => {
        setPronosticosGuardados(prev => ({
            ...prev,
            [partidoId]: {
                ...(prev[partidoId] || {}),
                [campo]: parseInt(valor) || 0
            }
        }));
    };

    // Guardar pronóstico
    const guardarPronostico = async (partidoId) => {
        try {
            const pronostico = pronosticosGuardados[partidoId];
            await axiosInstance.post("/pronosticos", {
                partidoId,
                golesLocal: pronostico.golesLocal,
                golesVisitante: pronostico.golesVisitante,
                usuarioId: usuario.id
            });
            alert("Pronóstico guardado correctamente");
        } catch (err) {
            console.error("Error al guardar pronóstico:", err);
            alert("Error al guardar pronóstico");
        }
    };

    if (loading) {
        return (
            <div className="flex justify-center items-center h-64">
                <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-600"></div>
                <span className="ml-3 text-gray-600">Cargando partidos...</span>
            </div>
        );
    }

    if (error) {
        return (
            <div className="text-center py-12">
                <p className="text-red-500 text-lg">{error}</p>
            </div>
        );
    }

    return (
        <div className="container mx-auto px-4 py-8">
            <div className="mb-8">
                <h1 className="text-3xl font-bold text-gray-800 mb-2">Mis Pronósticos</h1>
                <p className="text-gray-600">
                    Realiza tus pronósticos para los próximos partidos
                </p>
            </div>

            {partidos.length === 0 ? (
                <div className="text-center py-12">
                    <p className="text-gray-500 text-lg">
                        No hay partidos disponibles para pronosticar
                    </p>
                </div>
            ) : (
                <div className="grid gap-6 md:grid-cols-2 lg:grid-cols-3">
                    {partidos.map(partido => (
                        <PartidoPronosticoCard
                            key={partido.id}
                            partido={partido}
                            pronostico={pronosticosGuardados[partido.id]}
                            onChange={handlePronosticoChange}
                            onSave={guardarPronostico}
                        />
                    ))}
                </div>
            )}
        </div>
    );
};

export default PronosticosParent;*/
