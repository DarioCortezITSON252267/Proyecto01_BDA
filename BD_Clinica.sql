-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS clinica;
USE clinica;

-- Tabla USUARIOS
CREATE TABLE Usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido_paterno VARCHAR(50) NOT NULL,
    apellido_materno VARCHAR(50) NOT NULL,
    correo VARCHAR(100) UNIQUE NULL,
    contraseña VARCHAR(255) NOT NULL,
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

-- Tabla TELEFONOS
CREATE TABLE Telefonos (
    id_telefono INT AUTO_INCREMENT PRIMARY KEY,
    numero VARCHAR(10) NOT NULL,
    id_usuario INT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario) ON DELETE CASCADE
);

-- Tabla PACIENTES
CREATE TABLE Pacientes (
    id_paciente INT AUTO_INCREMENT PRIMARY KEY,
    fecha_nacimiento DATE NOT NULL,
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

-- Insertar Médicos (Usuarios + Médicos)
INSERT INTO Usuarios (nombre, apellido_paterno, apellido_materno, correo, contraseña, tipo_usuario) VALUES
('Gregory', 'House', 'Laurie', NULL, 'house123', 'medico'),
('Ana', 'López', 'Martínez', NULL, 'ana456', 'medico'),
('Javier', 'Ramírez', 'Díaz', NULL, 'javier789', 'medico'),
('María', 'González', 'Fernández', NULL, 'maria101', 'medico'),
('Fernando', 'Torres', 'Sánchez', NULL, 'fernando202', 'medico');

-- Asociar médicos a los usuarios
INSERT INTO Medicos (especialidad, cedula, id_usuario) VALUES
('Neurología', '12345678', 1),
('General', '87654321', 2),
('Hematología', '23456789', 3),
('General', '34567890', 4),
('Cardiología', '45678901', 5);

-- Insertar Horarios para los médicos
INSERT INTO Horarios (horaInicial, horaFinal, dias, id_medico) VALUES
('08:00:00', '14:00:00', 'Lunes a Viernes', 1),
('09:00:00', '15:00:00', 'Lunes a Sábado', 2),
('10:00:00', '16:00:00', 'Martes a Jueves', 3),
('12:00:00', '18:00:00', 'Miércoles a Viernes', 4),
('14:00:00', '20:00:00', 'Lunes, Miércoles y Viernes', 5);

-- Trigger para registrar la cancelacion de una cita.
DELIMITER $$

CREATE TRIGGER registrar_cancelacion
AFTER UPDATE ON Cita
FOR EACH ROW
BEGIN
    IF NEW.estado = 'Cancelada' THEN
        INSERT INTO HistorialCancelaciones (id_cita, id_paciente, fecha_cancelacion)
        VALUES (NEW.id_cita, NEW.id_paciente, NOW());
    END IF;
END$$

DELIMITER ;

-- Trigger  para evitar doble cita en el mismo horario
DELIMITER $$

CREATE TRIGGER evitar_doble_cita
BEFORE INSERT ON Cita
FOR EACH ROW
BEGIN
    DECLARE existe INT;
    
    -- Contamos cuántas citas tiene el paciente en la misma fecha
    SELECT COUNT(*) INTO existe
    FROM Cita
    WHERE id_paciente = NEW.id_paciente AND DATE(fechahora) = DATE(NEW.fechahora)
    AND estado NOT IN ('Cancelada');

    -- Si ya tiene una cita, lanzamos un error
    IF existe > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El paciente ya tiene una cita en esta fecha';
    END IF;
END$$

DELIMITER ;

INSERT INTO Usuarios(nombre, apellido_paterno, apellido_materno, telefono, correo) 
VALUES ('Angel','Servin de la mora','Vazquez','6441545454','angel.ser@gmail.com');

INSERT INTO Direcciones(calle, numero, colonia, codigo_postal) 
VALUES('itson',3211,'Villa itson',543533);

INSERT INTO Pacientes(fecha_nacimiento, id_usuario, id_direccion) 
VALUES ('2025-11-07',1,1);


INSERT INTO Usuarios(nombre, apellido_paterno, apellido_materno, telefono, correo) 
VALUES ('Angel','Servin de la mora','Vazquez','6441545454','angel.ser@gmail.com');

INSERT INTO Direcciones(calle, numero, colonia, codigo_postal) 
VALUES('itson',3211,'Villa itson',543533);

INSERT INTO Pacientes(fecha_nacimiento, id_usuario, id_direccion) 
VALUES ('2025-11-07',1,1);

DELIMITER $$

CREATE PROCEDURE RegistrarPaciente(
    IN p_nombre VARCHAR(100),
    IN p_apellido_paterno VARCHAR(100),
    IN p_apellido_materno VARCHAR(100),
    IN p_telefono VARCHAR(20),
    IN p_correo VARCHAR(100),
    IN p_fecha_nacimiento DATE,
    IN p_calle VARCHAR(50),
    IN p_numero VARCHAR(10),
    IN p_colonia VARCHAR(50),
    IN p_codigo_postal INT
)
BEGIN
    DECLARE usuarioID INT;
    DECLARE direccionID INT;
    DECLARE mensaje_error VARCHAR(255);
    
    -- Manejo de errores
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
        ROLLBACK;
        SET mensaje_error = 'Error: No se pudo registrar el paciente';
        SELECT mensaje_error AS mensaje;
    END;
    
    -- Iniciar la transacción
    START TRANSACTION;

    -- Insertar en la tabla Usuarios
    INSERT INTO Usuarios (nombre, apellido_paterno, apellido_materno, telefono, correo)
    VALUES (p_nombre, p_apellido_paterno, p_apellido_materno, p_telefono, p_correo);

    -- Obtener el ID del usuario recién insertado
    SET usuarioID = LAST_INSERT_ID();

    -- Insertar en la tabla Direcciones
    INSERT INTO Direcciones (calle, numero, colonia, codigo_postal)
    VALUES (p_calle, p_numero, p_colonia, p_codigo_postal);

    -- Obtener el ID de la dirección recién insertada
    SET direccionID = LAST_INSERT_ID();

    -- Insertar en la tabla Pacientes
    INSERT INTO Pacientes (fecha_nacimiento, id_usuario, id_direccion)
    VALUES (p_fecha_nacimiento, usuarioID, direccionID);

    -- Confirmar la transacción
    COMMIT;

    SELECT 'Paciente registrado exitosamente' AS mensaje;

END$$

DELIMITER ;



