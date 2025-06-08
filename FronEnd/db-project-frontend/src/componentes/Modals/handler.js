import axiosInstance from "../../utils/axiosInstance";

const api = axiosInstance;

export const getPartidosWithTorneoId = ({
  torneoId,
  setPartidos,
  pagination,
  setTotalPages,
}) => {
  api
    .get(`/partidos/torneo/${torneoId}`, {
      params: {
        page: pagination.currentPage,
        size: pagination.currentSize,
      },
    })
    .then((response) => {
      setPartidos(response.data.partidos);
      setTotalPages(
        Math.ceil(response.data.totalMatches / pagination.currentSize)
      );
    })
    .catch((error) => {
      console.error("Error fetching partidos:", error);
    });
};
