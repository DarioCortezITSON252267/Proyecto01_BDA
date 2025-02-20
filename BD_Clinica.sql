-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS clinica;
USE clinica;

-- Crear tabla USUARIOS
CREATE TABLE Usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido_paterno VARCHAR(50) NOT NULL,
    apellido_materno VARCHAR(50) NOT NULL
);

-- Crear tabla PACIENTES
CREATE TABLE Pacientes (
    id_paciente INT AUTO_INCREMENT PRIMARY KEY,
    edad INT NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    direccion VARCHAR(200) NOT NULL,
    telefonos VARCHAR(10) NOT NULL,
    id_usuario INT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Crear tabla MEDICOS
CREATE TABLE Medicos (
    id_medico INT AUTO_INCREMENT PRIMARY KEY,
    especialidad VARCHAR(20) NOT NULL,
    cedula VARCHAR(20) NOT NULL,
    id_usuario INT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE
);
-- Crear tabla Horarios
CREATE TABLE Horarios (
    id_horario INT AUTO_INCREMENT PRIMARY KEY,
    horaInicial TIME NOT NULL,
    horaFinal TIME NOT NULL,
    dias VARCHAR(50) NOT NULL,
	id_medico INT NOT NULL,
    FOREIGN KEY (id_medico) REFERENCES Medicos(id_medico) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Crear tabla CITAS
CREATE TABLE Cita (
    id_cita INT AUTO_INCREMENT PRIMARY KEY,
    estado VARCHAR(20) NOT NULL,
    fechahora DATETIME NOT NULL,
    id_paciente INT NOT NULL,
    id_medico INT NOT NULL,
    FOREIGN KEY (id_paciente) REFERENCES Pacientes(id_paciente) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_medico) REFERENCES Medicos(id_medico) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Crear tabla CONSULTA
CREATE TABLE Consulta (
    id_consulta INT AUTO_INCREMENT PRIMARY KEY,
    diagnostico TEXT NOT NULL,
    tratamiento TEXT NOT NULL,
    motivo TEXT NOT NULL,
     id_medico INT NOT NULL,
    id_cita INT NOT NULL,
    FOREIGN KEY (id_medico) REFERENCES Medicos(id_medico) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_cita) REFERENCES Cita(id_cita) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Crear tabla CONSULTASINCITA
CREATE TABLE ConsultaSinCita (
    id_consulta INT AUTO_INCREMENT PRIMARY KEY,
    folio VARCHAR(20) NOT NULL, 
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    id_medico INT NOT NULL,
    id_paciente INT NOT NULL,
    FOREIGN KEY (id_medico) REFERENCES Medicos(id_medico) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_paciente) REFERENCES Pacientes(id_paciente) ON DELETE CASCADE ON UPDATE CASCADE
);
-- Insertar usuarios (Médicos)
INSERT INTO Usuarios (nombre, apellido_paterno, apellido_materno) VALUES
('Gregory', 'House', 'Laurie'),
('Ana', 'López', 'Martínez'),
('Javier', 'Ramírez', 'Díaz'),
('María', 'González', 'Fernández'),
('Fernando', 'Torres', 'Sánchez');

-- Insertar médicos asociados a los usuarios
INSERT INTO Medicos (especialidad, cedula,id_usuario) VALUES
('Neurología', '12345678',1),
('General', '87654321',2),
('Hematologia', '23456789',3),
('General', '34567890',4),
('Cardiologia', '45678901',5);

-- Insertar horarios de los médicos
INSERT INTO Horarios (horaInicial, horaFinal, dias, id_medico) VALUES
('08:00:00', '14:00:00', 'Lunes a Viernes', 1),
('09:00:00', '15:00:00', 'Lunes a Sábado', 2),
('10:00:00', '16:00:00', 'Martes a Jueves', 3),
('12:00:00', '18:00:00', 'Miércoles a Viernes', 4),
('14:00:00', '20:00:00', 'Lunes, Miércoles y Viernes', 5);


-- Verificar Usuarios
SELECT * FROM Usuarios;

-- Verificar Medicos
SELECT * FROM Medicos;

-- Verificar Horarios
SELECT * FROM Horarios;




