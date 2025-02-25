 -- DROP DATABASE Clinica;
CREATE DATABASE Clinica;
USE Clinica;


-- Tabla USUARIOS
CREATE TABLE Usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(50) UNIQUE NOT NULL,
    contraseña VARCHAR(100) NOT NULL,
    tipo_usuario ENUM('paciente', 'medico') NOT NULL
);

-- Tabla DIRECCIONES
CREATE TABLE Direcciones (
    id_direccion INT AUTO_INCREMENT PRIMARY KEY,
    calle VARCHAR(100) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    colonia VARCHAR(50) NOT NULL,
    codigo_postal VARCHAR(5) NOT NULL,
    id_usuario INT UNIQUE NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario) ON DELETE CASCADE
);


-- Tabla PACIENTES
CREATE TABLE Pacientes (
    id_paciente INT AUTO_INCREMENT PRIMARY KEY,
    fecha_nacimiento DATE NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    apellido_paterno VARCHAR(50) NOT NULL,
    apellido_materno VARCHAR(50)  NULL,
    correo VARCHAR(100) UNIQUE NULL,
    telefono VARCHAR(20) NOT NULL,
    id_usuario INT UNIQUE NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario) ON DELETE CASCADE
);

-- Tabla MÉDICOS
CREATE TABLE Medicos (
    id_medico INT AUTO_INCREMENT PRIMARY KEY,
    especialidad VARCHAR(50) NOT NULL,
    cedula VARCHAR(20) UNIQUE NOT NULL,
    id_usuario INT UNIQUE NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario) ON DELETE CASCADE
);

-- Tabla HORARIOS
CREATE TABLE Horarios (
    id_horario INT AUTO_INCREMENT PRIMARY KEY,
    horaInicial TIME NOT NULL,
    horaFinal TIME NOT NULL,
    dias VARCHAR(50) NOT NULL,
    id_medico INT NOT NULL,
    FOREIGN KEY (id_medico) REFERENCES Medicos(id_medico) ON DELETE CASCADE
);

-- Tabla CITAS
CREATE TABLE Cita (
    id_cita INT AUTO_INCREMENT PRIMARY KEY,
    estado ENUM('pendiente', 'confirmada', 'cancelada') NOT NULL DEFAULT 'pendiente',
    fechahora DATETIME NOT NULL,
    nota TEXT NULL,
    id_paciente INT NOT NULL,
    id_medico INT NOT NULL,
    FOREIGN KEY (id_paciente) REFERENCES Pacientes(id_paciente) ON DELETE CASCADE,
    FOREIGN KEY (id_medico) REFERENCES Medicos(id_medico) ON DELETE CASCADE
);

-- Tabla CONSULTA
CREATE TABLE Consulta (
    id_consulta INT AUTO_INCREMENT PRIMARY KEY,
    motivo TEXT NOT NULL,
    diagnostico TEXT NOT NULL,
    tratamiento TEXT NOT NULL,
    id_cita INT UNIQUE NOT NULL,
    FOREIGN KEY (id_cita) REFERENCES Cita(id_cita) ON DELETE CASCADE
);

-- Tabla CONSULTA SIN CITA
CREATE TABLE ConsultaSinCita (
    id_consulta INT AUTO_INCREMENT PRIMARY KEY,
    folio VARCHAR(20) UNIQUE NOT NULL, 
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    motivo TEXT NOT NULL,
    id_medico INT NOT NULL,
    id_paciente INT NOT NULL,
    FOREIGN KEY (id_medico) REFERENCES Medicos(id_medico) ON DELETE CASCADE,
    FOREIGN KEY (id_paciente) REFERENCES Pacientes(id_paciente) ON DELETE CASCADE
);

-- Tabla Auditoria
CREATE TABLE Auditoria (
    id_auditoria INT AUTO_INCREMENT PRIMARY KEY,
    tabla_afectada ENUM('Cita', 'Consulta', 'ConsultaSinCita') NOT NULL,
    accion ENUM('insertar', 'actualizar', 'eliminar') NOT NULL,
    fecha_hora DATETIME NOT NULL,
    datos_anteriores TEXT NULL, 
    datos_nuevos TEXT NULL,
	id_cita INT NULL,
    id_consulta INT NULL,
    id_consultasincita INT NULL
);

-- TRIGGERS PARA AUDITORÍA
DELIMITER $$

-- Trigger para insertar en Cita
CREATE TRIGGER insertar_cita
AFTER INSERT ON Cita FOR EACH ROW
BEGIN
    INSERT INTO Auditoria (tabla_afectada, id_cita, accion, fecha_hora, datos_nuevos) 
    VALUES ('Cita', NEW.id_cita, 'insertar', NOW(), 
    CONCAT('estado:', NEW.estado, ', fechahora:', NEW.fechahora, ', id_paciente:', NEW.id_paciente, ', id_medico:', NEW.id_medico, ', nota:', NEW.nota));
END $$

-- Trigger para actualizar en Cita
CREATE TRIGGER actualizar_cita
AFTER UPDATE ON Cita FOR EACH ROW
BEGIN
    INSERT INTO Auditoria (tabla_afectada, id_cita, accion, fecha_hora, datos_anteriores, datos_nuevos) 
    VALUES ('Cita', NEW.id_cita, 'actualizar', NOW(), 
    CONCAT('estado:', OLD.estado, ', fechahora:', OLD.fechahora, ', id_paciente:', OLD.id_paciente, ', id_medico:', OLD.id_medico, ', nota:', OLD.nota), 
    CONCAT('estado:', NEW.estado, ', fechahora:', NEW.fechahora, ', id_paciente:', NEW.id_paciente, ', id_medico:', NEW.id_medico, ', nota:', NEW.nota));
END $$


-- Trigger para insertar en Consulta
CREATE TRIGGER insertar_consulta
AFTER INSERT ON Consulta FOR EACH ROW
BEGIN
    INSERT INTO Auditoria (tabla_afectada, id_consulta, accion, fecha_hora, datos_nuevos) 
    VALUES ('Consulta', NEW.id_consulta, 'insertar', NOW(), 
    CONCAT('motivo:', NEW.motivo, ', diagnostico:', NEW.diagnostico, ', tratamiento:', NEW.tratamiento, ', id_cita:', NEW.id_cita));
END $$

-- Trigger para actualizar en Consulta
CREATE TRIGGER actualizar_consulta
AFTER UPDATE ON Consulta FOR EACH ROW
BEGIN
    INSERT INTO Auditoria (tabla_afectada, id_consulta, accion, fecha_hora, datos_anteriores, datos_nuevos) 
    VALUES ('Consulta', NEW.id_consulta, 'actualizar', NOW(), 
    CONCAT('motivo:', OLD.motivo, ', diagnostico:', OLD.diagnostico, ', tratamiento:', OLD.tratamiento, ', id_cita:', OLD.id_cita), 
    CONCAT('motivo:', NEW.motivo, ', diagnostico:', NEW.diagnostico, ', tratamiento:', NEW.tratamiento, ', id_cita:', NEW.id_cita));
END $$


-- Trigger para insertar en ConsultaSinCita
CREATE TRIGGER insertar_consulta_sin_cita
AFTER INSERT ON ConsultaSinCita FOR EACH ROW
BEGIN
    INSERT INTO Auditoria (tabla_afectada, id_consultasincita, accion, fecha_hora, datos_nuevos) 
    VALUES ('ConsultaSinCita', NEW.id_consulta, 'insertar', NOW(), 
    CONCAT('folio:', NEW.folio, ', fecha:', NEW.fecha, ', hora:', NEW.hora, ', motivo:', NEW.motivo, ', id_medico:', NEW.id_medico, ', id_paciente:', NEW.id_paciente));
END $$

-- Trigger para actualizar en ConsultaSinCita
CREATE TRIGGER actualizar_consulta_sin_cita
AFTER UPDATE ON ConsultaSinCita FOR EACH ROW
BEGIN
    INSERT INTO Auditoria (tabla_afectada, id_consultasincita, accion, fecha_hora, datos_anteriores, datos_nuevos) 
    VALUES ('ConsultaSinCita', NEW.id_consulta, 'actualizar', NOW(), 
    CONCAT('folio:', OLD.folio, ', fecha:', OLD.fecha, ', hora:', OLD.hora, ', motivo:', OLD.motivo, ', id_medico:', OLD.id_medico, ', id_paciente:', OLD.id_paciente), 
    CONCAT('folio:', NEW.folio, ', fecha:', NEW.fecha, ', hora:', NEW.hora, ', motivo:', NEW.motivo, ', id_medico:', NEW.id_medico, ', id_paciente:', NEW.id_paciente));
END $$


DELIMITER ;

INSERT INTO Usuarios (usuario, contraseña, tipo_usuario)
VALUES ('angel.ser', 'contraseña123', 'paciente');

INSERT INTO Direcciones (calle, numero, colonia, codigo_postal, id_usuario)
VALUES ('itson', '3211', 'Villa itson', 54333, 1);


INSERT INTO Pacientes (fecha_nacimiento, nombre, apellido_paterno, apellido_materno, telefono, id_usuario)
VALUES ('2000-11-07', 'Angel', 'Servin de la mora', 'Vazquez', '6441545454', 1);


INSERT INTO Usuarios (usuario, contraseña, tipo_usuario)  
VALUES ('dr.garcia', 'clave123', 'medico');

SET @id_usuario_medico = LAST_INSERT_ID();

INSERT INTO Medicos (especialidad, cedula, id_usuario)  
VALUES ('Cardiología', 'ABC123456', @id_usuario_medico);

DELIMITER $$

CREATE PROCEDURE RegistrarPaciente(
    IN p_usuario VARCHAR(50),
    IN p_contraseña VARCHAR(100),
    IN p_nombre VARCHAR(50),
    IN p_apellido_paterno VARCHAR(50),
    IN p_apellido_materno VARCHAR(50),
    IN p_telefono VARCHAR(20),
    IN p_correo VARCHAR(100),
    IN p_fecha_nacimiento DATE,
    IN p_calle VARCHAR(100),
    IN p_numero VARCHAR(10),
    IN p_colonia VARCHAR(50),
    IN p_codigo_postal VARCHAR(5)
)
BEGIN
    DECLARE usuarioID INT;
    DECLARE mensaje_error VARCHAR(255);

    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
        ROLLBACK;
        SET mensaje_error = 'Error: No se pudo registrar el paciente';
        SELECT mensaje_error AS mensaje;
    END;

    START TRANSACTION;

    -- Insertar en la tabla Usuarios
    INSERT INTO Usuarios (usuario, contraseña, tipo_usuario)
    VALUES (p_usuario, p_contraseña, 'paciente');

    SET usuarioID = LAST_INSERT_ID();

    -- Insertar en la tabla Direcciones
    INSERT INTO Direcciones (calle, numero, colonia, codigo_postal, id_usuario)
    VALUES (p_calle, p_numero, p_colonia, p_codigo_postal, usuarioID);

    -- Insertar en la tabla Pacientes (permitiendo apellido materno NULL)
    INSERT INTO Pacientes (fecha_nacimiento, nombre, apellido_paterno, apellido_materno, telefono, correo, id_usuario)
    VALUES (p_fecha_nacimiento, p_nombre, p_apellido_paterno, 
            IF(p_apellido_materno = '', NULL, p_apellido_materno), -- Convierte '' en NULL
            p_telefono, p_correo, usuarioID);

    COMMIT;

    SELECT 'Paciente registrado exitosamente' AS mensaje;
END$$

DELIMITER ;


DELIMITER $$

DELIMITER $$

DELIMITER $$

CREATE PROCEDURE actualizarPacientePorId(
    IN idPaciente INT,
    IN fechaNacimientoNuevo DATE,
    IN nombreNuevo VARCHAR(50),
    IN apellidoPaternoNuevo VARCHAR(50),
    IN apellidoMaternoNuevo VARCHAR(50),
    IN telefonoNuevo VARCHAR(20),
    IN calleNueva VARCHAR(100),
    IN numeroNuevo VARCHAR(20),
    IN coloniaNueva VARCHAR(50),
    IN codigoPostalNuevo VARCHAR(5)
)
BEGIN
    DECLARE usuarioID INT;
    DECLARE paciente_existe INT;
    DECLARE mensaje_error VARCHAR(255);

    -- Manejo de errores
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
        ROLLBACK;
        SET mensaje_error = 'Error: No se pudo actualizar el paciente';
        SELECT mensaje_error AS mensaje;
    END;

    -- Verificar si el paciente existe
    SELECT COUNT(*) INTO paciente_existe
    FROM Pacientes
    WHERE id_paciente = idPaciente;

    IF paciente_existe = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: El paciente no existe';
    END IF;

    -- Obtener el id_usuario del paciente
    SELECT id_usuario INTO usuarioID
    FROM Pacientes
    WHERE id_paciente = idPaciente;

    START TRANSACTION;

    -- Actualizar la dirección del usuario
    UPDATE Direcciones
    SET calle = calleNueva,
        numero = numeroNuevo,
        colonia = coloniaNueva,
        codigo_postal = codigoPostalNuevo
    WHERE id_usuario = usuarioID;

    -- Actualizar los datos del paciente (SIN modificar correo)
    UPDATE Pacientes
    SET nombre = nombreNuevo,
        apellido_paterno = apellidoPaternoNuevo,
        apellido_materno = IF(apellidoMaternoNuevo = '', NULL, apellidoMaternoNuevo),
        telefono = telefonoNuevo,
        fecha_nacimiento = fechaNacimientoNuevo
    WHERE id_paciente = idPaciente;

    COMMIT;

    SELECT 'Paciente actualizado exitosamente' AS mensaje;
END$$

DELIMITER ;


DELIMITER ;

DELIMITER ;

DELIMITER $$
CREATE PROCEDURE VerHistorialCitas(IN idPaciente INT)
BEGIN
    SELECT 
        c.id_cita, 
        c.estado, 
        c.fechahora, 
        c.nota, 
        m.id_medico, 
        m.especialidad
    FROM Cita c
    INNER JOIN Medicos m ON c.id_medico = m.id_medico
    WHERE c.id_paciente = idPaciente
    ORDER BY c.fechahora DESC;
END $$
DELIMITER ;

DELIMITER $$

CREATE PROCEDURE AgendarCita(
    IN p_id_paciente INT,
    IN p_id_medico INT,
    IN p_fechahora DATETIME,
    IN p_estado ENUM('pendiente', 'confirmada', 'cancelada'),
    IN p_nota TEXT
)
BEGIN
    DECLARE paciente_existe INT;
    DECLARE medico_existe INT;
    DECLARE mensaje_error VARCHAR(255);
    
    -- Manejo de errores
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
        ROLLBACK;
        SET mensaje_error = 'Error: No se pudo agendar la cita';
        SELECT mensaje_error AS mensaje;
    END;

    -- Verificar que el paciente exista
    SELECT COUNT(*) INTO paciente_existe FROM Pacientes WHERE id_paciente = p_id_paciente;
    
    -- Verificar que el médico exista
    SELECT COUNT(*) INTO medico_existe FROM Medicos WHERE id_medico = p_id_medico;
    
    -- Si el paciente o el médico no existen, no se agenda la cita
    IF paciente_existe = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: El paciente no existe';
    END IF;
    
    IF medico_existe = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: El médico no existe';
    END IF;
    
    -- Transacción para insertar la cita
    START TRANSACTION;
    
    INSERT INTO Cita (estado, fechahora, nota, id_paciente, id_medico)
    VALUES (p_estado, p_fechahora, p_nota, p_id_paciente, p_id_medico);
    
    COMMIT;
    
    -- Confirmación
    SELECT 'Cita agendada exitosamente' AS mensaje;
END $$

DELIMITER ;


INSERT INTO Cita (estado, fechahora, nota, id_paciente, id_medico)  
VALUES ('pendiente', '2025-03-01 10:00:00', 'Primera consulta', 1, 1);

/* INSERT INTO Cita (estado, fechahora, nota, id_paciente, id_medico)  
VALUES ('pendiente', '2025-03-01 10:30:00', 'Segunda consulta', 2, 1);
*/

-- ==================================
--                MEDICO
-- ==================================

-- VALIDACION MEDICOS
DELIMITER $$
CREATE PROCEDURE ValidarMedico(
    IN p_cedula VARCHAR(20),
    IN p_contraseña VARCHAR(100)
)
BEGIN
    SELECT u.id_usuario, u.usuario 
    FROM Usuarios u
    INNER JOIN Medicos m ON u.id_usuario = m.id_usuario
    WHERE m.cedula = p_cedula AND u.contraseña = SHA2(p_contraseña, 256);
END $$
DELIMITER ;

-- PROCEDIMIENTO DE BAJA
DELIMITER $$
CREATE PROCEDURE DarDeBajaMedico(
    IN p_usuario VARCHAR(50),
    IN p_cedula VARCHAR(20),
    IN p_contraseña VARCHAR(100)
)
BEGIN
    DECLARE v_id_medico INT;
    DECLARE v_citas_pendientes INT;
    DECLARE v_id_usuario INT;

    -- Verificar que el usuario, cédula y contraseña coincidan
    SELECT u.id_usuario INTO v_id_usuario
    FROM Usuarios u
    INNER JOIN Medicos m ON u.id_usuario = m.id_usuario
    WHERE u.usuario = p_usuario AND m.cedula = p_cedula AND u.contraseña = SHA2(p_contraseña, 256);

    -- Si no se encuentra el médico, lanzar un error
    IF v_id_usuario IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Usuario, cédula o contraseña incorrectos.';
    END IF;

    -- Obtener el ID del médico basado en la cédula
    SELECT id_medico INTO v_id_medico
    FROM Medicos
    WHERE cedula = p_cedula;

    -- Verificar si hay citas pendientes
    SELECT COUNT(*) INTO v_citas_pendientes
    FROM Cita
    WHERE id_medico = v_id_medico AND estado = 'pendiente';

    -- Si hay citas pendientes, lanzar un error
    IF v_citas_pendientes > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'No se puede dar de baja al médico porque tiene citas pendientes.';
    ELSE
        -- Si no hay citas pendientes, eliminar al médico
        DELETE FROM Medicos WHERE cedula = p_cedula;
        DELETE FROM Usuarios WHERE id_usuario = v_id_usuario;
    END IF;
END $$
DELIMITER ;

-- ---VER CITAS PENDIENTES
DELIMITER $$
CREATE PROCEDURE VerCitasPendientes(IN p_id_medico INT)
BEGIN
    SELECT 
        C.id_cita,
        C.fechahora,
        C.nota,
        P.nombre AS nombre_paciente,
        P.apellido_paterno AS apellido_paciente
    FROM 
        Cita C
    INNER JOIN 
        Pacientes P ON C.id_paciente = P.id_paciente
    WHERE 
        C.id_medico = p_id_medico
        AND C.estado = 'pendiente';
END $$
DELIMITER ;




-- ==================================
--          DATOS CARGADOS
-- ==================================

-- USUARIO - MEDICO
INSERT INTO Usuarios (usuario, contraseña, tipo_usuario)
VALUES ('Dr.House', SHA2('Housedr147', 256), 'medico'),
('Dr.Ramirez', SHA2('Ramirez489', 256), 'medico'),
('Dr.Hernandez', SHA2('Hernandoc219', 256), 'medico'), 
('Dr.Suarez', SHA2('Suarezpp952', 256), 'medico'),
('Dr.Cruz', SHA2('Cruz573', 256), 'medico'),
('Dr.Torres', SHA2('Torres911', 256), 'medico'),
('Dr.PEPE', SHA2('Pepe111', 256), 'medico');

-- MEDICOS
INSERT INTO Medicos (especialidad, cedula, id_usuario)
VALUES ('Cardiología', '123456', 1),
('Truamatismo', '654321', 2),
('General', '987654', 3),
('Pediatria', '456789', 4),
('General', '147852', 5),
('Ortopedia', '639852', 6),
('Oncologia', '111111', 7);

-- HORARIOS MEDICO
INSERT INTO Horarios (horaInicial, horaFinal, dias, id_medico)
VALUES ('09:00:00', '17:00:00', 'Lunes a Viernes', 1),
('08:00:00', '14:00:00', 'Lunes, Miércoles, Viernes', 2),
('10:00:00', '19:00:00', 'Martes y Jueves', 3),
('07:00:00', '15:00:00', 'Lunes a Viernes', 4),
('12:00:00', '20:00:00', 'Lunes a Sábado', 5),
('12:00:00', '20:00:00', 'Miercoles a Lunes', 6),
('12:00:00', '20:00:00', 'Jueves a Martes', 7);