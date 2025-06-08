import { useEffect, useState } from 'react';
import axiosInstance from "../../utils/axiosInstance";

const Pronosticos = ({ usuario }) => {
    const [pronosticos, setPronosticos] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchPronosticos = async () => {
            try {
                const response = await axiosInstance.get(`/pronosticos/inscripcion/${usuario.idInscripcion || 1}`);
                setPronosticos(response.data);
            } catch (error) {
                console.error('Error al obtener pronósticos:', error);
            } finally {
                setLoading(false);
            }
        };

        fetchPronosticos();
    }, [usuario.idInscripcion]);

    if (loading) return <div>Cargando pronósticos...</div>;

    return (
        <div className="container mx-auto p-4">
            <h2 className="text-2xl font-bold mb-4">Mis Pronósticos</h2>

            {pronosticos.length === 0 ? (
                <p>No tienes pronósticos registrados</p>
            ) : (
                <div className="grid gap-4">
                    {pronosticos.map(pronostico => (
                        <div key={pronostico.id} className="p-4 border rounded-lg">
                            <h3 className="font-semibold">
                                {pronostico.partido.equipoLocal.nombre} vs {pronostico.partido.equipoVisitante.nombre}
                            </h3>
                            <p>Fecha: {new Date(pronostico.partido.fechaHora).toLocaleDateString()}</p>
                            <p>Pronóstico: {pronostico.golesLocal} - {pronostico.golesVisita}</p>
                            <p>Estado: {pronostico.partido.estado}</p>
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
};

export default Pronosticos;