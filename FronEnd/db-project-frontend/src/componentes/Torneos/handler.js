import axiosInstance from "../../utils/axiosInstance";

const api = axiosInstance;

export const getTorneos = ({
  setTorneos,
  setTotalPages,
  setLoading,
  pagination,
}) => {
  setLoading(true);
  api
    .get("/torneos", {
      params: {
        page: pagination.currentPage,
        size: pagination.currentSize,
      },
    })
    .then((response) => {
      setTorneos(response.data.torneos);
      setTotalPages(
        Math.ceil(response.data.totalTorneos / pagination.currentSize)
      );
      setLoading(false);
    })
    .catch((error) => {
      console.error("Error fetching torneos:", error);
      setLoading(false);
    });
};
