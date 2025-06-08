import axiosInstance from "../../utils/axiosInstance.js";
import { jwtDecode } from "jwt-decode";

const api = axiosInstance;

export const handleLogin = (user, pwrd, setUsuarioActual) => {
  api
    .post("/login", null, {
      headers: {
        Accept: "*/*",
      },
      auth: {
        username: user,
        password: pwrd,
      },
    })
    .then((response) => {
      const token = response.data;
      const decodedToken = jwtDecode(token);
      const usuario = {
        id: decodedToken.id,
        sub: decodedToken.sub,
        name: decodedToken.name,
        scope: decodedToken.scope,
        birthDate: decodedToken.birthDate,
      };
      localStorage.setItem("token", token);
      localStorage.setItem("usuarioActual", JSON.stringify(usuario));
      setUsuarioActual(usuario);
    })
    .catch((error) => {
      console.error("Error al iniciar sesión:", error);
    });
};

export const handleLogout = (
  setUsuarioActual,
  setVistaActual,
  removeFromStorage,
  setTorneoSeleccionado
) => {
  setUsuarioActual(null);
  removeFromStorage("usuarioActual");
  localStorage.removeItem("token");
  setVistaActual("login");
  setTorneoSeleccionado(null);
};
