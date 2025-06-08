import React from 'react';

const Navbar = ({ usuario, onLogout }) => {
  return (
    <nav className="bg-blue-600 text-white p-4 shadow-md">
      <div className="mx-auto flex justify-between items-center">
        <div className="flex flex-col items-center space-x-4">
          <h1 className="text-xl font-bold">Quiniela</h1>
          <h2 className="text-sm">{usuario.scope}</h2>
        </div>
        {usuario && (
          <div className="flex items-center space-x-4">
            <span>Hola, {usuario.name}</span>
            <button
              onClick={onLogout}
              className="px-3 py-1 bg-white text-blue-600 rounded hover:bg-gray-100">
              Cerrar Sesión
            </button>
          </div>
        )}
      </div>
    </nav>
  );
};

export default Navbar;