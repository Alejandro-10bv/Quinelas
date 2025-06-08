import MenuBar from "../Layout/MenuBar";
import { useState } from "react";
import TorneoParent from "../Torneos/TorneosParent";
import PartidosParent from "../Partidos/PartidosParent";
import PronosticosParent from "../pronosticos/PronosticosParent.jsx";
import QuinielasParent from "../Quinielas/QuinielasParent";

const MainView = ({ usuario }) => {
  const [currentView, setCurrentView] = useState({});

  const onViewChange = (view) => {
    setCurrentView(view);
  };

  const renderCurrentView = (view) => {
    switch (view.id) {
      case "partidos":
        return <PartidosParent usuario={usuario} />;
      case "torneos":
        return <TorneoParent usuario={usuario} />;
      case "quinielas":
        return <QuinielasParent usuario={usuario} />;
      case "pronosticos":
        return <PronosticosParent usuario={usuario} />
      case "rankings":
        return <div>Rankings</div>;
      default:
        return null;
    }
  };

  return (
    <div className="flex flex-row h-screen bg-gray-100">
      <MenuBar eventDispatcher={onViewChange} />
      <div className="flex-1 p-6 bg-white overflow-y-auto">
        {renderCurrentView(currentView)}
      </div>
    </div>
  );
};

export default MainView;
