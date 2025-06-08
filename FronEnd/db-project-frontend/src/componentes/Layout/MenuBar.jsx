import { useEffect, useState } from "react";

const menuItems = [
  {
    id: "partidos",
    label: "Partidos",
    ADMINISTRADOR: ["read", "write"],
    JUGADOR: ["read"],
    icon: "⚽",
  },
  {
    id: "torneos",
    label: "Torneos",
    ADMINISTRADOR: ["read", "write"],
    JUGADOR: ["read"],
    icon: "🏆",
  },
  {
    id: "quinielas",
    label: "Quinielas",
    ADMINISTRADOR: ["read", "write"],
    JUGADOR: ["read"],
    icon: "🎫",
  },
  {
    id: "pronosticos",
    label: "Pronosticos",
    ADMINISTRADOR: ["read"],
    JUGADOR: ["read", "write"],
    icon: "📊",
  },
  {
    id: "rankings",
    label: "Rankings",
    ADMINISTRADOR: ["read"],
    JUGADOR: ["read"],
    icon: "🏅",
  },
];

const MenuBar = ({ eventDispatcher }) => {
  const [activeItem, setActiveItem] = useState("partidos");
  useEffect(() => {
    eventDispatcher(menuItems[0]);
  }, []);

  const handleItemClick = (item) => {
    setActiveItem(item.id);
    const selectedItem = menuItems.find((i) => i.id === item.id);
    eventDispatcher(selectedItem);
  };

  return (
    <nav className="bg-blue-600 text-white w-64 min-h-screen shadow-lg">
      {/* Lista del menú */}
      <ul className="pt-6">
        {menuItems.map((item) => (
          <li key={item.id}>
            <button
              onClick={() => handleItemClick(item)}
              className={`
                flex items-center w-full px-6 py-4 text-white text-left
                hover:bg-blue-700 transition-colors duration-200 
                border-l-4 hover:border-blue-300
                ${
                  activeItem === item.id
                    ? "bg-blue-700 border-blue-300"
                    : "border-transparent"
                }
              `}>
              <span className="mr-3 text-lg">{item.icon}</span>
              <span className="font-medium">{item.label}</span>
            </button>
          </li>
        ))}
      </ul>
    </nav>
  );
};

export default MenuBar;
