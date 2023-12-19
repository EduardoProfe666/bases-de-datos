# CONEST. Proyecto de Bases de Datos

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Eclipse](https://img.shields.io/badge/Eclipse-FE7A16.svg?style=for-the-badge&logo=Eclipse&logoColor=white)
![License](https://img.shields.io/badge/MIT-e63caa?style=for-the-badge&label=License&labelColor=35495E)

## ✨ Descripción

Proyecto de curso de la asignatura Bases de Datos de una aplicación para el control docente: CONEST.
La misma permite a los usuarios, según sus roles y privilegios, acceder a ciertas informaciones y funcionalidades.

## 😎 Tecnologías empleadas

- `Java` 1.8
- `Postgres` 9.5
- `Java Swing`
- `FlatLaf`
- `JasperReports`
- `Dotenv`
- Biblioteca de componentes personalizada

## 🌌 Descarga de la aplicación

Para poder descargar el proyecto acceda al [siguiente enlace](https://github.com/EduardoProfe666/bases-de-datos/releases/latest)

## 💻 Pasos para ejecutar el proyecto

> [!WARNING]
> Se requiere tener instalados Java>=1.8 y Postgres 9.5 para el correcto funcionamiento del proyecto. Puede que se requiere ajustar el `Compiler Compliance Level` a 1.8 para la ejecución correcta del proyecto en Eclipse

1. Instalar la base de datos proporcionada en la carpeta `/db/` siguiente las indicaciones ahí presentes
1. Modificar los archivos `.env` deseados con la información de acceso a la base de datos. Si se requiere ejecutar el `.jar`, modifique el archivo `db.env`. Si se requiere ejecutar el proyecto, modifique el archivo `src/utils/bd_data/bd_eduardo.env`, o cree un nuevo archivo `.env` en esta ruta y cambie la propiedad `ENV_TO_LOAD` en la clase `src/definitions/LogicDefinitions.java`
1. Ejecutar el `.jar` o ejecutar el proyecto a través de `src/init/Main.java`

## 👾 SplashScreen y Login

> SplashScreen
>
> ![1](/assets/1.png)

> Login
>
> ![1](/assets/2.png)

## 💫 Roles y Privilegios

### 🕵 Administrador

Superusuario que puede crear otros usuarios.

#### 🖼 Capturas de pantalla

> Usuarios
>
> ![1](/assets/3.png)

### 💥 Secretario Docente

Usuario que tiene acceso a toda la información, pudiendo crear, editar y eliminar. Las principales funcionalidades son:

- Crear y editar estudiantes
- Eliminar bajas del sistema
- Agregar/Editar las evaluaciones de todos los estudiantes
- Ver todas las asignaturas
- Ver el escalafón de todos los años y grupos del curso actual
- Ver todos los reportes existentes y parametrizarlos.

#### 🖼 Capturas de pantalla

> Alumnos
>
> ![1](/assets/7.png)

> Bajas
>
> ![1](/assets/8.png)

> Asignaturas
>
> ![1](/assets/9.png)

> Evaluaciones
>
> ![1](/assets/10.png)

> Escalafón
>
> ![1](/assets/11.png)

> Selección de Reportes
>
> ![1](/assets/12.png)

### 🚢 Estudiante

Usuario que tiene acceso a su información general, sus evaluaciones y el escalafón de su grupo y año, a menos que el mismo sea baja.

#### 🖼 Capturas de pantalla

> Inicio
>
> ![1](/assets/4.png)

> Evaluaciones
>
> ![1](/assets/5.png)

> Escalafón
>
> ![1](/assets/6.png)

## 🤓 Cuentas de prueba

Si la base de datos contiene los datos de prueba proporcionados, entonces puede acceder a la aplicación a través de las siguientes cuentas:

- `Administrador`: **correo ->** `admin` | **contraseña ->** `1234`
- `Secretario Docente`: **correo ->** `secretario` | **contraseña ->** `1234`
- `Estudiante`: **correo ->** `estudiante` | **contraseña ->** `1234`
