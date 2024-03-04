# IETI-BOOKING-SYSTEM

Proyecto Integrador: Booking System REST API.

## Comenzando 🚀

Las siguientes instrucciones le permitirán obtener una copia del proyecto en funcionamiento en su máquina local para fines de desarrollo y prueba.

### Requisitos 📋

* [Git](https://git-scm.com/) - Control de versiones
* [Maven](https://maven.apache.org/) - Manejador de dependencias
* [Java](https://www.oracle.com/java/technologies/downloads/#java17) - Lenguaje de programación

> [!IMPORTANT]
> Es necesario tener instalado Git, Maven y Java 17 para poder ejecutar el proyecto.

### Instalación 🔧

Realice los siguientes pasos para clonar el proyecto en su máquina local.

```bash
git clone https://github.com/ELS4NTA/IETI-BOOKING-SYSTEM.git
cd IETI-BOOKING-SYSTEM/

```

## Ejecutando la aplicación ⚙️

Para ejecutar la aplicación, ejecute el siguiente comando:

```bash
mvn clean spring-boot:run

```

## Primera parte: Configuración inicial 1️⃣

Verificación del primer endpoint de la aplicación [http://localhost:8080/health](http://localhost:8080/health).

![image](https://github.com/ELS4NTA/IETI-BOOKING-SYSTEM/assets/99996670/6d5c7217-97e3-4bd9-be61-f3724bed42fa)

## Segunda parte: Controlador y servicio de usuarios 2️⃣

Operaciones CRUD para usuarios y verificación de los endpoints de la aplicación con [Postman](https://www.postman.com/).

$\color{Yellow}\large{\textbf{POST}}$

![image](https://github.com/ELS4NTA/IETI-BOOKING-SYSTEM/assets/99996670/f672a5b1-3f12-41da-a94c-9c09771b23bd)

$\color{Green}\large{\textbf{GET}}$

![image](https://github.com/ELS4NTA/IETI-BOOKING-SYSTEM/assets/99996670/27c608ec-70e0-41d2-9799-6f0d565fc0df)

$\color{RoyalBlue}\large{\textbf{PUT}}$

![image](https://github.com/ELS4NTA/IETI-BOOKING-SYSTEM/assets/99996670/6c0e2aad-32c3-402c-84ec-a4123baf322d)

$\color{OrangeRed}\large{\textbf{DELETE}}$

![image](https://github.com/ELS4NTA/IETI-BOOKING-SYSTEM/assets/99996670/454f9dfc-4597-4c16-b872-2ecb3a52a55e)

## Tercera parte: Persistencia con Spring Data MongoDB. 3️⃣

Crear un cluster en Atlas MongoDB y configurar la conexión con la aplicación.

![image](https://github.com/ELS4NTA/IETI-BOOKING-SYSTEM/assets/99996670/dae0e0ec-1aa4-41d5-afd1-b2c91c89f43e)

![image](https://github.com/ELS4NTA/IETI-BOOKING-SYSTEM/assets/99996670/a623ec3b-6d15-46df-95e7-97fb6f2b5739)

![image](https://github.com/ELS4NTA/IETI-BOOKING-SYSTEM/assets/99996670/2f16659a-1e42-4a11-8042-74573e39226e)

Configuración de la conexión con la base de datos.

```properties
spring.data.mongodb.uri=mongodb+srv://<username>:<password>@<cluster>.mongodb.net/mydatabase
spring.data.mongodb.database=mydatabase
```

Implementar la interfaz y el servicio para realizar las operaciones CRUD sobre el modelo almacenado en la base de datos.

Con el uso de [Postman](https://www.postman.com/), se verifica la persistencia de los datos en la base de datos, realizando las operaciones CRUD como en la segunda parte.

![image](https://github.com/ELS4NTA/IETI-BOOKING-SYSTEM/assets/99996670/e7300599-baa8-4baf-827f-10d059c2aa1c)

## Cuarta parte: Seguridad con JWT 4️⃣

Implementar la seguridad con JWT en la aplicación.

Se crea la configuración de seguridad con JWT y un filtro en las clases `SecurityConfig` y `JWTAuthorizationFilter`.

En esta habilitamos el endpoint `/v1/users` con el metodo `POST` para registrar un usuario.

![image](https://github.com/ELS4NTA/IETI-BOOKING-SYSTEM/assets/99996670/b04cae8b-00b6-49c3-9c92-8e27ae0fffd1)

![image](https://github.com/ELS4NTA/IETI-BOOKING-SYSTEM/assets/99996670/5b0ff2b3-ae89-44bf-bed6-866ae181b596)

Luego de esto vamos al endpoint `/v1/auth` con el metodo `POST` para obtener el token de autenticación.

![image](https://github.com/ELS4NTA/IETI-BOOKING-SYSTEM/assets/99996670/14ba2195-d922-4cfc-a29e-68c6a8803ff5)

## Versionado 📌

  ![IETI BOOKING SYSTEM](https://img.shields.io/badge/IETI_BOOKING_SYSTEM-v1.0.0-blue)

## Autores ✒️

* **Daniel Santanilla** - [ELS4NTA](https://github.com/ELS4NTA)

## Licencia 📄

[![License: CC BY-SA 4.0](https://licensebuttons.net/l/by-sa/4.0/88x31.png)](https://creativecommons.org/licenses/by-sa/4.0/deed.en)

Este proyecto está bajo la licencia de Creative Commons Reconocimiento-CompartirIgual 4.0 Internacional (CC BY-SA 4.0) - Ver el archivo [LICENSE](LICENSE) para más detalles.
