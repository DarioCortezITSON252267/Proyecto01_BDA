-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS clinica;
USE clinica;

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
    codigo_postal INT NOT NULL,
    id_usuario INT UNIQUE NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario) ON DELETE CASCADE
);


-- Tabla PACIENTES
CREATE TABLE Pacientes (
    id_paciente INT AUTO_INCREMENT PRIMARY KEY,
    fecha_nacimiento DATE NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    apellido_paterno VARCHAR(50) NOT NULL,
    apellido_materno VARCHAR(50) NOT NULL,
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
VALUES ('itson', '3211', 'Villa itson', 543533, 1);


INSERT INTO Pacientes (fecha_nacimiento, nombre, apellido_paterno, apellido_materno, telefono, id_usuario)
VALUES ('2000-11-07', 'Angel', 'Servin de la mora', 'Vazquez', '6441545454', 1);

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
    IN p_codigo_postal INT
)
BEGIN
    DECLARE usuarioID INT;
    DECLARE direccionID INT;
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

    -- Obtener el ID del usuario recién insertado
    SET usuarioID = LAST_INSERT_ID();

    -- Insertar en la tabla Direcciones
    INSERT INTO Direcciones (calle, numero, colonia, codigo_postal, id_usuario)
    VALUES (p_calle, p_numero, p_colonia, p_codigo_postal, usuarioID);

    -- Obtener el ID de la dirección recién insertada
    SET direccionID = LAST_INSERT_ID();

    -- Insertar en la tabla Pacientes
    INSERT INTO Pacientes (fecha_nacimiento, nombre, apellido_paterno, apellido_materno, telefono, correo, id_usuario)
    VALUES (p_fecha_nacimiento, p_nombre, p_apellido_paterno, p_apellido_materno, p_telefono, p_correo, usuarioID);

    -- Confirmar la transacción
    COMMIT;

    SELECT 'Paciente registrado exitosamente' AS mensaje;
END$$

DELIMITER ;

