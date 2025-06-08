import Env from "../Env";
import axios from 'axios';


const basicUrl = Env.API_BASIC_URL;
const axiosInstance = axios.create({
  baseURL: Env.API_BASIC_URL,
  withCredentials: true,
});

axiosInstance.interceptors.request.use((config) => {
  const token = localStorage.getItem("token");
  if (token) {
    config.headers["Authorization"] = `Bearer ${token}`;
  }
  return config;
});

export default axiosInstance;
