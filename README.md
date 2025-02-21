# Proyecto01_BDA
 Proyecto No.1 de la materia de Base de Datos Avanzadas con maestra Maria de los Angeles German Vazquez - Aplicacion Java con JDBC.

# Sistema de Gestión de Pacientes - Clínica

Este proyecto es un sistema de gestión para una clínica, en el cual se pueden registrar pacientes, médicos, citas, consultas, entre otros. Está desarrollado en **Java** con **MySQL** para la base de datos. Utiliza **NetBeans** como entorno de desarrollo integrado (IDE). La base de datos está diseñada para manejar usuarios, pacientes, médicos, citas, y auditoría de operaciones.

## Estructura del proyecto

El proyecto está organizado en varias capas:

1. **Capa de presentación**: Interfaz de usuario donde los médicos y pacientes pueden interactuar con el sistema.
2. **Capa de negocio**: Lógica que maneja las operaciones relacionadas con usuarios, citas, pacientes y médicos.
3. **Capa de persistencia**: Interacción con la base de datos para almacenar, actualizar y eliminar datos de pacientes, médicos y citas.

## Funcionalidades

### Pacientes:
- **Registro de paciente**: Permite registrar un paciente con sus datos personales y de contacto.
- **Agendar cita**: Los pacientes pueden agendar citas con médicos.
- **Consultar y cancelar citas**: Los pacientes pueden consultar el estado de sus citas y cancelarlas si es necesario.

### Médicos:
- **Consultar agenda**: Los médicos pueden consultar las citas programadas para el día.
- **Registrar diagnóstico y tratamiento**: Los médicos pueden registrar diagnósticos y tratamientos para las citas realizadas.
- **Dar de baja**: Los médicos pueden darse de baja del sistema con confirmación de contraseña.

### Base de datos:
- **Tablas principales**: Usuarios, Pacientes, Médicos, Citas, Consultas, Direcciones, Horarios y Auditoria.
- **Transacciones y procedimientos almacenados**: Para manejar operaciones atómicas y asegurar la integridad de los datos.
- **Triggers**: Para mantener un registro de las operaciones realizadas sobre las tablas `Cita`, `Consulta` y `ConsultaSinCita` en la tabla de auditoría.

