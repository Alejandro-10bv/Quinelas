USE DB_Project;
GO

CREATE PROCEDURE test_sp_actualizar_puntuaciones_acierta_ganador
AS
BEGIN
    SET NOCOUNT ON;

    BEGIN TRY
        BEGIN TRANSACTION;

        DECLARE 
            @ID_Rol INT,
            @ID_Usuario UNIQUEIDENTIFIER,
            @ID_Torneo INT,
            @ID_EquipoLocal INT,
            @ID_EquipoVisitante INT,
            @ID_TipoPuntuacion INT,
            @ID_Quiniela INT,
            @ID_Inscripcion INT,
            @ID_Partido INT,
            @ID_Pronostico INT,
            @PuntosAsignados INT;

        -- Buscar rol de usuario jugador
        SELECT TOP 1
        @ID_Rol = ID
    FROM Roles
    WHERE nombre = 'Jugador';

        -- Insertar usuario
        INSERT INTO Usuarios
        (contrasenha, nombre, apellido_1, apellido_2, fecha_nacimiento, correo_electronico, ID_rol)
    VALUES
        ('1234', 'Luis', 'Gomez', 'Perez', '1990-01-01', 'luis_Gomez_' + CAST(GETDATE() AS VARCHAR(36)) + '@example.com', @ID_Rol);

        SELECT TOP 1
        @ID_Usuario = ID
    FROM Usuarios
    WHERE correo_electronico = 'luis_Gomez_' + CAST(GETDATE() AS VARCHAR(36)) + '@example.com';

        -- Insertar torneo
        INSERT INTO Torneos
        (nombre, descripcion)
    VALUES
        ('Torneo Test', 'Torneo de prueba');

        SET @ID_Torneo = SCOPE_IDENTITY();

        -- Insertar equipos
        INSERT INTO Equipos
        (nombre)
    VALUES
        ('Equipo A');
        SET @ID_EquipoLocal = SCOPE_IDENTITY();

        INSERT INTO Equipos
        (nombre)
    VALUES
        ('Equipo B');
        SET @ID_EquipoVisitante = SCOPE_IDENTITY();

        -- Seleccionar tipo de puntuación
        SELECT TOP 1
        @ID_TipoPuntuacion = ID
    FROM Tipos_Puntuaciones
    WHERE nombre = 'Acierta ganador';

        -- Insertar quiniela
        INSERT INTO Quinielas
        (nombre, descripcion, es_publica, fecha_inicio, fecha_cierre, estado, ID_tipo_puntuacion, ID_torneo)
    VALUES
        ('Quiniela Test', 'Prueba de quiniela', 1, GETDATE(), DATEADD(DAY, 1, GETDATE()), 'ABIERTA', @ID_TipoPuntuacion, @ID_Torneo);

        SET @ID_Quiniela = SCOPE_IDENTITY();

        -- Inscribir usuario
        INSERT INTO Inscripciones
        (ID_usuario, ID_quiniela, cumple_condiciones, acepta_reglas)
    VALUES
        (@ID_Usuario, @ID_Quiniela, 1, 1);

        SET @ID_Inscripcion = SCOPE_IDENTITY();

        -- Insertar partido (sin goles aún)
        INSERT INTO Partidos
        (fecha_hora, ID_equipo_local, ID_equipo_visitante, ID_torneo)
    VALUES
        (DATEADD(HOUR, 1, GETDATE()), @ID_EquipoLocal, @ID_EquipoVisitante, @ID_Torneo);

        SET @ID_Partido = SCOPE_IDENTITY();

        -- Insertar pronóstico del usuario (gana local 2-0)
        INSERT INTO Pronosticos
        (ID_partido, ID_inscripcion, goles_local, goles_visita, fecha_hora)
    VALUES
        (@ID_Partido, @ID_Inscripcion, 2, 0, GETDATE());

        SET @ID_Pronostico = SCOPE_IDENTITY();

        -- Actualizar resultado del partido (local gana 3-1)
        UPDATE Partidos
        SET goles_local = 3, goles_visita = 1
        WHERE ID = @ID_Partido;

        -- Verificar puntos
        SELECT @PuntosAsignados = puntos
    FROM Puntuaciones
    WHERE ID_inscripcion = @ID_Inscripcion AND ID_pronostico = @ID_Pronostico;

        IF @PuntosAsignados <> 3 
        BEGIN
        PRINT '❌ Test FALLÓ: Puntos incorrectos asignados';
        PRINT 'Puntos esperados: 3';
        PRINT 'Puntos obtenidos: ' + CAST(@PuntosAsignados AS NVARCHAR(10));
        ROLLBACK;
        RETURN;
    END
        ELSE
        BEGIN
        PRINT '✅ Test PASÓ: Puntos correctamente asignados';
    END

    SELECT * FROM vw_ranking_quiniela;

        -- ROLLBACK para no dejar rastros
        ROLLBACK;
    END TRY
    BEGIN CATCH
    -- Manejo de errores
        ROLLBACK;
        PRINT '❌ Test FALLÓ: ' + ERROR_MESSAGE();
    END CATCH
END
GO

EXEC test_sp_actualizar_puntuaciones_acierta_ganador;