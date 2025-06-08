-- Write your own SQL object definition here, and it'll be included in your package.
CREATE DATABASE DB_Project;
GO

-- Creates the login AbolrousHazem with password '340$Uuxwp7Mcxo7Khy'.
CREATE LOGIN DevLogin WITH PASSWORD = 'MSSql';
GO

-- Creates a database user for the login created previously.
USE DB_Project;
GO

CREATE USER DevLogin FROM LOGIN DevLogin

-- Asignar permisos necesarios (puedes ajustar según necesidad)
ALTER ROLE db_owner ADD MEMBER DevLogin;
GO

EXEC xp_instance_regwrite N'HKEY_LOCAL_MACHINE',
N'Software\Microsoft\MSSQLServer\MSSQLServer',
N'LoginMode',
REG_DWORD,
2;
GO

DROP TABLE roles;

DROP TABLE usuarios;

DROP TABLE torneos;

DROP TABLE equipos;

DROP TABLE estadios;

DROP TABLE partidos;

DROP TABLE tipos_puntuaciones;

DROP TABLE quinielas;

DROP TABLE inscripciones;

DROP TABLE pronosticos;

DROP TABLE premios;

DROP TABLE puntuaciones;

CREATE TABLE roles
(
    ID INT PRIMARY KEY IDENTITY(1, 1),
    nombre NVARCHAR(50) NOT NULL UNIQUE,
    CONSTRAINT chk_rol CHECK (
        nombre IN ('ADMINISTRADOR', 'JUGADOR')
    )
);
GO

DROP TABLE IF EXISTS usuarios;

CREATE TABLE usuarios
(
    ID UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWSEQUENTIALID(),
    contrasenha VARCHAR(255) NOT NULL,
    nombre NVARCHAR(50) NOT NULL,
    apellido_1 NVARCHAR(50) NOT NULL,
    apellido_2 NVARCHAR(50) DEFAULT NULL,
    fecha_nacimiento DATE NOT NULL,
    correo_electronico NVARCHAR(50) NOT NULL UNIQUE,
    ID_rol INT NOT NULL,
    CONSTRAINT fk_usuario_rol FOREIGN KEY (ID_rol) REFERENCES roles (ID) ON DELETE NO ACTION
);
GO

DROP TABLE IF EXISTS torneos;

CREATE TABLE torneos
(
    ID INT PRIMARY KEY IDENTITY(1, 1),
    nombre NVARCHAR(50) NOT NULL,
    descripcion NVARCHAR(255) NOT NULL
);
GO

DROP TABLE IF EXISTS equipos;

CREATE TABLE equipos
(
    ID INT PRIMARY KEY IDENTITY(1, 1),
    nombre NVARCHAR(50) NOT NULL
);
GO

DROP TABLE IF EXISTS estadios;

CREATE TABLE estadios
(
    ID INT PRIMARY KEY IDENTITY(1, 1),
    ID_equipo INT DEFAULT NULL,
    nombre NVARCHAR(50) NOT NULL,
    CONSTRAINT fk_estadio_equipo FOREIGN KEY (ID_equipo) REFERENCES equipos (ID) ON DELETE SET NULL,
    CONSTRAINT uq_estadio UNIQUE (nombre),
    CONSTRAINT uq_equipo UNIQUE (ID_equipo)
);
GO

DROP TABLE IF EXISTS partidos;

CREATE TABLE partidos
(
    ID INT PRIMARY KEY IDENTITY(1, 1),
    fecha_hora DATETIMEOFFSET(6) NOT NULL,
    ID_equipo_local INT NOT NULL,
    ID_equipo_visitante INT NOT NULL,
    goles_local INT DEFAULT NULL,
    goles_visita INT DEFAULT NULL,
    ID_torneo INT DEFAULT NULL,
    CONSTRAINT fk_partido_equipo_local FOREIGN KEY (ID_equipo_local) REFERENCES equipos (ID) ON DELETE NO ACTION,
    CONSTRAINT fk_partido_equipo_visitante FOREIGN KEY (ID_equipo_visitante) REFERENCES equipos (ID) ON DELETE NO ACTION,
    CONSTRAINT fk_partido_torneo FOREIGN KEY (id_torneo) REFERENCES torneos (ID) ON DELETE SET NULL,
    CONSTRAINT chk_equipo_local CHECK (
        ID_equipo_local <> ID_equipo_visitante
    ),
    CONSTRAINT chk_partido_goles_local CHECK (goles_local >= 0),
    CONSTRAINT chk_partido_goles_visita CHECK (goles_visita >= 0)
);
GO

DROP TABLE IF EXISTS tipos_puntuaciones;

CREATE TABLE tipos_puntuaciones
(
    ID INT PRIMARY KEY IDENTITY(1, 1),                              -- Identificador único del tipo de puntuación
    nombre NVARCHAR(50) NOT NULL,                                   -- Nombre del tipo de puntuación
    puntos_obtenidos INT NOT NULL,                                  -- Puntos que se obtienen por acertar el pronóstico
    CONSTRAINT uq_nombre_puntos UNIQUE (nombre, puntos_obtenidos)
);
GO

DROP TABLE IF EXISTS quinielas;

CREATE TABLE quinielas
(
    ID INT PRIMARY KEY IDENTITY(1, 1),
    nombre NVARCHAR(50) NOT NULL,
    descripcion NVARCHAR(255) NOT NULL,
    es_publica BIT NOT NULL DEFAULT 0,
    fecha_inicio DATETIMEOFFSET(6) NOT NULL,
    fecha_cierre DATETIMEOFFSET(6) NOT NULL,
    estado NVARCHAR(50) NOT NULL DEFAULT 'ABIERTA',
    ID_tipo_puntuacion INT NOT NULL,
    ID_torneo INT NOT NULL,
    CONSTRAINT fk_quiniela_tipo_puntuacion FOREIGN KEY (ID_tipo_puntuacion) REFERENCES tipos_puntuaciones (ID) ON DELETE NO ACTION,
    CONSTRAINT fk_quiniela_torneo FOREIGN KEY (ID_torneo) REFERENCES torneos (ID) ON DELETE CASCADE,
    CONSTRAINT chk_estado CHECK (
        estado IN (
            'ABIERTA',
            'CERRADA',
            'FINALIZADA'
        )
    ),
    CONSTRAINT chk_fecha_inicio CHECK (fecha_inicio <= fecha_cierre),
    CONSTRAINT chk_fecha_cierre CHECK (fecha_cierre >= GETDATE())
);
GO

DROP TABLE IF EXISTS inscripciones;

CREATE TABLE inscripciones
(
    ID INT PRIMARY KEY IDENTITY(1, 1),
    ID_usuario UNIQUEIDENTIFIER NOT NULL,
    ID_quiniela INT NOT NULL,
    cumple_condiciones BIT NOT NULL DEFAULT 0,
    acepta_reglas BIT NOT NULL DEFAULT 0,
    CONSTRAINT fk_inscripcion_usuario FOREIGN KEY (ID_usuario) REFERENCES usuarios (ID) ON DELETE CASCADE,
    CONSTRAINT fk_inscripcion_quiniela FOREIGN KEY (ID_quiniela) REFERENCES quinielas (ID) ON DELETE CASCADE
);
GO

DROP TABLE IF EXISTS pronosticos;

-- Esta tabla es para guardar los pronosticos de cada usuario
-- en cada partido. Ademas, los pronosticos pueden ser unicamente
-- uno por usuario por partido.
CREATE TABLE pronosticos
(
    ID INT PRIMARY KEY IDENTITY(1, 1),
    ID_partido INT NOT NULL,
    ID_inscripcion INT NOT NULL,
    goles_local INT DEFAULT 0,
    goles_visita INT DEFAULT 0,
    fecha_hora DATETIMEOFFSET(6) NOT NULL DEFAULT SYSDATETIMEOFFSET(),
    CONSTRAINT fk_pronostico_partido FOREIGN KEY (ID_partido) REFERENCES partidos (ID) ON DELETE NO ACTION,
    CONSTRAINT fk_pronostico_inscripcion FOREIGN KEY (ID_inscripcion) REFERENCES inscripciones (ID) ON DELETE CASCADE,
    CONSTRAINT uq_pronostico UNIQUE (ID_partido, ID_inscripcion),
    CONSTRAINT chk_pronostico_goles_local CHECK (goles_local >= 0),
    CONSTRAINT chk_pronostico_goles_visita CHECK (goles_visita >= 0)
);
GO

DROP TABLE IF EXISTS premios;

CREATE TABLE premios
(
    ID INT PRIMARY KEY IDENTITY(1, 1),
    nombre NVARCHAR(50) NOT NULL,
    descripcion NVARCHAR(255) NOT NULL,
    condiciones NVARCHAR(255) NOT NULL,
    posicion_ranking INT NOT NULL,
    ID_quiniela INT NOT NULL,
    CONSTRAINT fk_premio_quiniela FOREIGN KEY (ID_quiniela) REFERENCES quinielas (ID) ON DELETE CASCADE,
);
GO

DROP TABLE IF EXISTS puntuaciones;

-- Esta tabla es para guardar los puntos que obtiene cada usuario
-- por cada pronostico que hace. Ademas, los puntos pueden ser
-- unicamente uno por usuario por pronostico.
CREATE TABLE puntuaciones
(
    ID INT PRIMARY KEY IDENTITY(1, 1),
    ID_inscripcion INT NOT NULL,
    ID_pronostico INT NOT NULL,
    puntos INT NOT NULL DEFAULT 0,
    CONSTRAINT fk_puntuaciones_inscripcion FOREIGN KEY (ID_inscripcion) REFERENCES inscripciones (ID) ON DELETE CASCADE,
    CONSTRAINT fk_puntuaciones_pronostico FOREIGN KEY (ID_pronostico) REFERENCES pronosticos (ID) ON DELETE NO ACTION,
    CONSTRAINT chk_puntos CHECK (puntos >= 0),
    CONSTRAINT uq_puntuacion UNIQUE (ID_inscripcion, ID_pronostico)
);
GO

DROP PRPOCEDURE
IF EXISTS sp_actualizar_puntuaciones_acierta_ganador;
GO

-- Procedimiento que se ejecuta para actualizar las puntuaciones
-- de los pronosticos de los usuarios cuando se actualiza el resultado
-- de un partido. Se basa en el tipo de puntuacion 'Acierta Ganador'.
CREATE PROCEDURE sp_actualizar_puntuaciones_acierta_ganador
    @ID_partido INT
AS
BEGIN
    SET NOCOUNT ON;

    -- Validar que el partido tenga marcador registrado
    IF EXISTS (
        SELECT 1
    FROM partidos
    WHERE ID = @ID_partido
        AND goles_local IS NOT NULL
        AND goles_visita IS NOT NULL
    )
    BEGIN
        -- Obtener ganador del partido
        DECLARE @goles_local INT, @goles_visita INT, @ganador VARCHAR(20);
        SELECT @goles_local = goles_local, @goles_visita = goles_visita
        FROM partidos
        WHERE ID = @ID_partido;

        IF @goles_local > @goles_visita
            SET @ganador = 'LOCAL';
        ELSE IF @goles_local < @goles_visita
            SET @ganador = 'VISITA';
        ELSE
            SET @ganador = 'EMPATE';

        -- Eliminar puntuaciones anteriores de este partido
        DELETE P
        FROM puntuaciones P
            JOIN pronosticos PR ON P.ID_pronostico = PR.ID
        WHERE PR.ID_partido = @ID_partido;

        -- Obtener ID y puntos del tipo de puntuación 'Acierta Ganador'
        DECLARE @ID_tipo_puntuacion INT, @puntos INT;
        SELECT TOP 1
            @ID_tipo_puntuacion = ID, @puntos = puntos_obtenidos
        FROM tipos_puntuaciones
        WHERE nombre = 'Acierta Ganador';

        -- Insertar nuevas puntuaciones para los pronósticos correctos
        INSERT INTO puntuaciones
            (ID_inscripcion, ID_pronostico, puntos)
        SELECT
            PR.ID_inscripcion,
            PR.ID,
            @puntos
        FROM pronosticos PR
            JOIN partidos PA ON PR.ID_partido = PA.ID
            JOIN inscripciones I ON PR.ID_inscripcion = I.ID
            JOIN quinielas Q ON I.ID_quiniela = Q.ID
        WHERE PR.ID_partido = @ID_partido
            AND Q.ID_tipo_puntuacion = @ID_tipo_puntuacion
            AND (
            (@ganador = 'LOCAL' AND PR.goles_local > PR.goles_visita)
            OR (@ganador = 'VISITA' AND PR.goles_local < PR.goles_visita)
            OR (@ganador = 'EMPATE' AND PR.goles_local = PR.goles_visita)
        );
    END
END;
GO

DROP TRIGGER IF EXISTS trg_ejecuta_actualizar_puntuaciones;
GO

-- Trigger que se ejecuta al actualizar el resultado de un partido
CREATE TRIGGER trg_ejecuta_actualizar_puntuaciones
ON partidos
AFTER UPDATE
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @ID_partido INT;

    -- Obtener el ID del partido modificado que ya tiene marcador
    SELECT @ID_partido = ID
    FROM inserted
    WHERE goles_local IS NOT NULL AND goles_visita IS NOT NULL;

    IF @ID_partido IS NOT NULL
    BEGIN
        EXEC sp_actualizar_puntuaciones_acierta_ganador @ID_partido;
    END
END;
GO

DROP VIEW IF EXISTS vw_ranking_quiniela;
GO

-- Crear vista para obtener el ranking de quinielas
CREATE VIEW vw_ranking_quiniela
AS
    SELECT
        i.ID_quiniela,
        u.nombre + ' ' + u.apellido_1 + ' ' + u.apellido_2 AS nombre_completo,
        ISNULL(SUM(p.puntos), 0) AS puntos_totales
    FROM
        inscripciones i
        INNER JOIN usuarios u ON i.ID_usuario = u.ID
        LEFT JOIN puntuaciones p ON p.ID_inscripcion = i.ID
    GROUP BY
    i.ID_quiniela,
    u.nombre,
    u.apellido_1,
    u.apellido_2;
GO