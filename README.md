# 🛡️ Backend Auth0 - Users and Roles

Este proyecto es una **API REST desarrollada en Spring Boot** que utiliza **Auth0** para la autenticación y autorización de usuarios, junto con una base de datos **MySQL** para persistencia.

---

## ⚙️ Configuración del entorno

Antes de ejecutar el proyecto, debés configurar las variables de entorno.  
Estas se encuentran normalmente en un archivo `.env` o directamente en el archivo `application.properties`.  
> 💡 Recomendado: usar `.env` para datos sensibles.

---

## 🔐 Variables de entorno requeridas

| Variable                                      | Descripción                                                                 |
|----------------------------------------------|-----------------------------------------------------------------------------|
| `spring.application.name`                    | Nombre de la aplicación. Por defecto: `auth0UsersAndRoles`.                |
| `spring.datasource.url`                      | URL de conexión a la base de datos MySQL. Ej: `jdbc:mysql://localhost:3306/mp`. |
| `spring.datasource.username`                 | Usuario de la base de datos MySQL.                                         |
| `spring.datasource.password`                 | Contraseña del usuario de la base de datos.                                |
| `spring.jpa.hibernate.ddl-auto`              | Estrategia de actualización del esquema. Usá `update` para desarrollo.     |
| `spring.jpa.database-platform`               | Dialecto de Hibernate. Para MySQL: `org.hibernate.dialect.MySQLDialect`.   |
| `server.port`                                | Puerto donde corre la API. Ej: `8081`.                                     |
| `spring.security.oauth2.resourceserver.jwt.issuer-uri` | URL del issuer de tu tenant de Auth0. Lo obtenés en el panel de Auth0.    |
| `auth0.audience`                             | Identificador de la API configurada en Auth0. Ej: `https://tuApiIdentifier`.|
| `logging.level.org.springframework.security` | Nivel de log para Spring Security. Ej: `INFO`, `DEBUG`.                    |
| `spring.websecurity.debug`                   | Activa/desactiva el debug de seguridad web (`true` o `false`).             |
| `web.cors.allowed-origins`                   | Orígenes permitidos por CORS. Ej: `http://localhost:5173` (si usás Vite).  |
| `auth0.domain`                               | Dominio de tu tenant en Auth0. Ej: `dev-xxxxxxx.us.auth0.com`.             |
| `auth0.clientId`                             | ID del cliente OAuth (Auth0).                                              |
| `auth0.clientSecret`                         | Secreto del cliente OAuth (**NO lo compartas públicamente**).             |

---

## 🧩 Inicialización: Configuración de Roles y Usuario Administrador

### 1) Setear Variables de Entorno
Asegurate de configurar correctamente **todas las variables de entorno necesarias** antes de ejecutar el proyecto.

---

### 2) Inicializar el Proyecto
Descomentá el bloque de código correspondiente en el archivo `main`.  
Esto hará lo siguiente:

- Creará los roles: **Administrador** y **Cliente**
- Generará el **usuario administrador** en la base de datos y en **Auth0**
- Con las credenciales de usuario administrador: mail y contraseña que le pusimos en el main podremos ingresar en el front como admin y seguir generando roles.

---

### ⚠️ Importante
**No olvides volver a comentar el bloque de inicialización** una vez completado este paso.  
Este código **solo debe ejecutarse una vez para la configuración inicial** del sistema.



