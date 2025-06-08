import axiosInstance from "../../utils/axiosInstance";

const api = axiosInstance;

export const getQuinielas = ({
  setQuinielas,
  setTotalPages,
  setLoading,
  pagination,
}) => {
  setLoading(true);
  api
    .get("/quinielas", {
      params: {
        page: pagination.currentPage,
        size: pagination.currentSize,
      },
    })
    .then((response) => {
      setQuinielas(response.data.quinielas);
      setTotalPages(
        Math.ceil(response.data.totalQuinielas / pagination.currentSize)
      );
      setLoading(false);
    })
    .catch((error) => {
      console.error("Error fetching quinielas:", error);
      setLoading(false);
    });
};