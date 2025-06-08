import { useState, useEffect } from "react";
import LoginForm from "./componentes/Login/LoginForm.jsx";
import Navbar from "./componentes/Layout/Navbar.jsx";
import { handleLogin, handleLogout } from "./componentes/Login/handler.js";
import MainView from "./componentes/vistas/MainView.jsx";
import { getFromStorage, removeFromStorage } from "./utils/storage";
import PartidosParent from './componentes/Partidos/PartidosParent';
import Pronosticos from './componentes/Pronosticos/Pronosticos';

const App = () => {
  const [usuarioActual, setUsuarioActual] = useState(
    getFromStorage("usuarioActual", null)
  );
  const [vistaActual, setVistaActual] = useState("login");
  const [torneoSeleccionado, setTorneoSeleccionado] = useState(null);

  useEffect(() => {
    usuarioActual ? setVistaActual("vistas") : setVistaActual("login");
  }, [usuarioActual]);

  const onLogin = (username, password) => {
    handleLogin(username, password, setUsuarioActual);
  };

  const onLogout = () => {
    handleLogout(
      setUsuarioActual,
      setVistaActual,
      removeFromStorage,
      setTorneoSeleccionado
    );
  };

  const renderView = () => {
    switch (vistaActual) {
      case "login":
        return <LoginForm onLogin={onLogin} />;
      case "vistas":
        return <MainView usuario={usuarioActual} />;
      default:
        return null;
    }
  };

  return (
    <div className="min-h-screen bg-gray-100">
      {usuarioActual && <Navbar usuario={usuarioActual} onLogout={onLogout} />}
      <main className="min-h-screen min-w-screen">{renderView()}</main>
    </div>
  );
};

export default App;
