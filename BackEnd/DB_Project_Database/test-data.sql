USE DB_Project;
GO

-- Equipos
INSERT INTO Equipos
    (nombre)
VALUES
    ('Tigres FC'),
    ('Leones FC'),
    ('Águilas Doradas'),
    ('Toros Rojos'),
    ('Panteras Negras'),
    ('Halcones Azules'),
    ('Lobos Grises'),
    ('Osos del Norte'),
    ('Tiburones del Sur'),
    ('Pumas Salvajes'),
    (N'Alajuelense'),
    (N'Saprissa'),
    (N'Herediano'),
    (N'Cartaginés'),
    (N'San Carlos'),
    (N'Pérez Zeledón'),
    (N'Grecia'),
    (N'Guanacasteca'),
    (N'Puntarenas FC'),
    (N'Sporting FC'),
    (N'Santos de Guápiles'),
    (N'Municipal Liberia');
GO

-- Estadios
INSERT INTO Estadios
    (ID_equipo, nombre)
VALUES
    (1, 'Estadio Tigre'),
    (2, 'Estadio León'),
    (3, 'Estadio Águila'),
    (4, 'Estadio Toro'),
    (5, 'Estadio Pantera'),
    (6, 'Estadio Halcón'),
    (7, 'Estadio Lobo'),
    (8, 'Estadio Oso'),
    (9, 'Estadio Tiburón'),
    (10, 'Estadio Puma'),
    (11, N'Estadio Alejandro Morera Soto'),
    (12, N'Estadio Ricardo Saprissa Aymá'),
    (13, N'Estadio Eladio Rosabal Cordero'),
    (14, N'Estadio José Rafael Fello Meza'),
    (15, N'Estadio Carlos Ugalde Álvarez'),
    (16, N'Estadio Municipal Pérez Zeledón'),
    (17, N'Estadio Allen Riggioni Suárez'),
    (18, N'Estadio Chorotega'),
    (19, N'Estadio Miguel Ángel “Lito” Pérez'),
    (20, N'Estadio Ernesto Rohrmoser'),
    (21, N'Estadio Ebal Rodríguez Aguilar'),
    (22, N'Estadio Edgardo Baltodano Briceño');
GO

-- Torneos
INSERT INTO Torneos
    (nombre, descripcion)
VALUES
    ('Liga Nacional 2025', 'Torneo de fútbol profesional entre los mejores equipos del país.'),
    (N'Primera División Costa Rica 2024-2025', N'Temporada completa con partidos de ida y vuelta');
GO

-- Partidos
INSERT INTO Partidos
    (fecha_hora, ID_equipo_local, ID_equipo_visitante, ID_torneo)
VALUES
    (GETDATE() + 1, 1, 2, 1),
    (GETDATE() + 2, 1, 3, 1),
    (GETDATE() + 3, 1, 4, 1),
    (GETDATE() + 4, 1, 5, 1),
    (GETDATE() + 5, 1, 6, 1),
    (GETDATE() + 6, 1, 7, 1),
    (GETDATE() + 7, 1, 8, 1),
    (GETDATE() + 8, 1, 9, 1),
    (GETDATE() + 9, 1, 10, 1),
    (GETDATE() + 10, 2, 3, 1),
    (GETDATE() + 11, 2, 4, 1),
    (GETDATE() + 12, 2, 5, 1),
    (GETDATE() + 13, 2, 6, 1),
    (GETDATE() + 14, 2, 7, 1),
    (GETDATE() + 15, 2, 8, 1),
    (GETDATE() + 16, 2, 9, 1),
    (GETDATE() + 17, 2, 10, 1),
    (GETDATE() + 18, 3, 4, 1),
    (GETDATE() + 19, 3, 5, 1),
    (GETDATE() + 20, 3, 6, 1),
    (GETDATE() + 21, 3, 7, 1),
    (GETDATE() + 22, 3, 8, 1),
    (GETDATE() + 23, 3, 9, 1),
    (GETDATE() + 24, 3, 10, 1),
    (GETDATE() + 25, 4, 5, 1),
    (GETDATE() + 26, 4, 6, 1),
    (GETDATE() + 27, 4, 7, 1),
    (GETDATE() + 28, 4, 8, 1),
    (GETDATE() + 29, 4, 9, 1),
    (GETDATE() + 30, 4, 10, 1),
    (GETDATE() + 31, 5, 6, 1),
    (GETDATE() + 32, 5, 7, 1),
    (GETDATE() + 33, 5, 8, 1),
    (GETDATE() + 34, 5, 9, 1),
    (GETDATE() + 35, 5, 10, 1),
    (GETDATE() + 36, 6, 7, 1),
    (GETDATE() + 37, 6, 8, 1),
    (GETDATE() + 38, 6, 9, 1),
    (GETDATE() + 39, 6, 10, 1),
    (GETDATE() + 40, 7, 8, 1),
    (GETDATE() + 41, 7, 9, 1),
    (GETDATE() + 42, 7, 10, 1),
    (GETDATE() + 43, 8, 9, 1),
    (GETDATE() + 44, 8, 10, 1),
    (GETDATE() + 45, 9, 10, 1),
    (GETDATE() + 46, 11, 12, 2),
    (GETDATE() + 47, 11, 13, 2),
    (GETDATE() + 48, 11, 14, 2),
    (GETDATE() + 49, 11, 15, 2),
    (GETDATE() + 50, 11, 16, 2),
    (GETDATE() + 51, 11, 17, 2),
    (GETDATE() + 52, 11, 18, 2),
    (GETDATE() + 53, 11, 19, 2),
    (GETDATE() + 54, 11, 20, 2),
    (GETDATE() + 55, 11, 21, 2),
    (GETDATE() + 56, 11, 22, 2),
    (GETDATE() + 57, 12, 13, 2),
    (GETDATE() + 58, 12, 14, 2),
    (GETDATE() + 59, 12, 15, 2),
    (GETDATE() + 60, 12, 16, 2),
    (GETDATE() + 61, 12, 17, 2),
    (GETDATE() + 62, 12, 18, 2),
    (GETDATE() + 63, 12, 19, 2),
    (GETDATE() + 64, 12, 20, 2),
    (GETDATE() + 65, 12, 21, 2),
    (GETDATE() + 66, 12, 22, 2),
    (GETDATE() + 67, 13, 14, 2),
    (GETDATE() + 68, 13, 15, 2),
    (GETDATE() + 69, 13, 16, 2),
    (GETDATE() + 70, 13, 17, 2),
    (GETDATE() + 71, 13, 18, 2),
    (GETDATE() + 72, 13, 19, 2),
    (GETDATE() + 73, 13, 20, 2),
    (GETDATE() + 74, 13, 21, 2),
    (GETDATE() + 75, 13, 22, 2),
    (GETDATE() + 76, 14, 15, 2),
    (GETDATE() + 77, 14, 16, 2),
    (GETDATE() + 78, 14, 17, 2),
    (GETDATE() + 79, 14, 18, 2),
    (GETDATE() + 80, 14, 19, 2),
    (GETDATE() + 81, 14, 20, 2),
    (GETDATE() + 82, 14, 21, 2),
    (GETDATE() + 83, 14, 22, 2),
    (GETDATE() + 84, 15, 16, 2),
    (GETDATE() + 85, 15, 17, 2),
    (GETDATE() + 86, 15, 18, 2),
    (GETDATE() + 88, 15, 19, 2),
    (GETDATE() + 89, 15, 20, 2),
    (GETDATE() + 90, 15, 21, 2),
    (GETDATE() + 91, 15, 22, 2),
    (GETDATE() + 92, 16, 17, 2),
    (GETDATE() + 93, 16, 18, 2),
    (GETDATE() + 94, 16, 19, 2),
    (GETDATE() + 95, 16, 20, 2),
    (GETDATE() + 96, 16, 21, 2),
    (GETDATE() + 97, 16, 22, 2),
    (GETDATE() + 98, 17, 18, 2),
    (GETDATE() + 99, 17, 19, 2),
    (GETDATE() + 100, 17, 20, 2),
    (GETDATE() + 101, 17, 21, 2),
    (GETDATE() + 102, 17, 22, 2),
    (GETDATE() + 103, 18, 19, 2),
    (GETDATE() + 104, 18, 20, 2),
    (GETDATE() + 105, 18, 21, 2);
GO

-- Tipos_Puntuaciones
INSERT INTO Tipos_Puntuaciones
    (nombre, puntos_obtenidos)
VALUES
    ('Acierta ganador', 3),
    ('Acierta marcador', 5);
GO

-- Quinielas
INSERT INTO Quinielas
    (nombre, descripcion, es_publica, fecha_inicio, fecha_cierre, ID_tipo_puntuacion, ID_torneo)
VALUES
    ('Quiniela Nacional', 'Predicciones del torneo nacional', 1, GETDATE(), GETDATE() + 30, 1, 1),
    ('Quiniela Avanzada', 'Predicción con marcador exacto', 0, GETDATE(), GETDATE() + 30, 2, 1),
    ('Quiniela Especial', 'Predicciones de partidos especiales', 1, GETDATE(), GETDATE() + 30, 1, 2),
    ('Quiniela Internacional', 'Predicciones de torneos internacionales', 1, GETDATE(), GETDATE() + 30, 2, 2);
GO

-- Premios para Quiniela 1
INSERT INTO Premios
    (nombre, descripcion, condiciones, posicion_ranking, ID_quiniela)
VALUES
    ('1er Lugar', 'Premio para el primer lugar', 'Mayor puntaje al cierre', 1, 1),
    ('2do Lugar', 'Premio para el segundo lugar', 'Segundo mayor puntaje', 2, 1),
    ('3er Lugar', 'Premio para el tercer lugar', 'Tercer mayor puntaje', 3, 1),
    ('1er Lugar', 'Premio para el primer lugar', 'Mayor puntaje al cierre', 1, 2),
    ('2do Lugar', 'Premio para el segundo lugar', 'Segundo mayor puntaje', 2, 2),
    ('3er Lugar', 'Premio para el tercer lugar', 'Tercer mayor puntaje', 3, 2),
    ('1er Lugar', 'Premio para el primer lugar', 'Mayor puntaje al cierre', 1, 3),
    ('2do Lugar', 'Premio para el segundo lugar', 'Segundo mayor puntaje', 2, 3),
    ('3er Lugar', 'Premio para el tercer lugar', 'Tercer mayor puntaje', 3, 3),
    ('1er Lugar', 'Premio para el primer lugar', 'Mayor puntaje al cierre', 1, 4),
    ('2do Lugar', 'Premio para el segundo lugar', 'Segundo mayor puntaje', 2, 4),
    ('3er Lugar', 'Premio para el tercer lugar', 'Tercer mayor puntaje', 3, 4);
GO

-- Inscripciones
INSERT INTO Inscripciones
    (ID_usuario, ID_quiniela, cumple_condiciones, acepta_reglas)
SELECT ID, 2, 1, 1
FROM Usuarios
WHERE correo_electronico = 'usuario@example.com';

-- Pronosticos para Quiniela 1
INSERT INTO Pronosticos
    (ID_partido, ID_inscripcion, goles_local, goles_visita, fecha_hora)
VALUES
    (36, 1, 1, 0, '2025-05-11 03:06:32'),
    (37, 1, 1, 0, '2025-05-11 03:11:32'),
    (38, 1, 1, 0, '2025-05-11 03:16:32'),
    (39, 1, 1, 0, '2025-05-11 03:21:32'),
    (40, 1, 1, 0, '2025-05-11 03:26:32'),
    (41, 1, 1, 0, '2025-05-11 03:31:32'),
    (42, 1, 1, 0, '2025-05-11 03:36:32'),
    (43, 1, 1, 0, '2025-05-11 03:41:32'),
    (44, 1, 1, 0, '2025-05-11 03:46:32'),
    (45, 1, 1, 0, '2025-05-11 03:51:32');

DECLARE @ID_USUARIO UNIQUEIDENTIFIER;
DECLARE @ID_QUINIELA INT;

-- Obtener ID del usuario por su correo
SELECT @ID_USUARIO = ID
FROM usuarios
WHERE correo_electronico = 'usuario@example.com';

-- Obtener ID de la quiniela por su nombre
SELECT @ID_QUINIELA = ID
FROM quinielas
WHERE nombre = N'Quiniela Especial';

-- Insertar inscripción en la quiniela
INSERT INTO inscripciones
    (
    ID_usuario,
    ID_quiniela,
    cumple_condiciones,
    acepta_reglas
    )
VALUES
    (
        @ID_USUARIO,
        @ID_QUINIELA,
        1, -- Supongamos que sí cumple condiciones
        1   -- Supongamos que aceptó reglas
);
