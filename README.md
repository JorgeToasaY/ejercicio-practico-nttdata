
# 🏦 Microservicio Banca - Despliegue con Docker

Este proyecto contiene dos microservicios desarrollados con **Java Spring Boot**:

- `msvc-customer-person`
- `msvc-account-movement`

Además, se utiliza **PostgreSQL** como base de datos y **RabbitMQ** como broker de mensajería para comunicación asincrónica entre servicios.

---

## 📦 Requisitos Previos

- Docker Desktop
- Java JDK 17
- Maven 3.9.9

---
![img.png](img.png)
## 🧱 Estructura del Proyecto

```
ejercicio-practico-nttdata/
│
├── msvc-customer-person/
│   ├── Dockerfile
│   ├── target/msvc-customer-person-0.0.1-SNAPSHOT.jar
│   └── ...
│
├── msvc-account-movement/
│   ├── Dockerfile
│   ├── target/msvc-account-movement-0.0.1-SNAPSHOT.jar
│   └── ...
│
├── BaseDatos.sql
├── docker-compose.yml
├── docker-compose.sh
├── Plan De Pruebas.docx
├── Microservicios_MiBanco_Postman_Collection.json
└── README.md
```

---

## 🚀 Pasos para Desplegar

## Ejecutar el archivo docker-compose.sh
### Paso 1. Abrir PowerShell como administrador 
### Paso 2. Dirigirse al directorio raiz del proyecto \ejercicio-practico-nttdata
### Ejemplo: cd D:\JORGE\APPS\ejercicio-practico-nttdata
### Paso 3. Copiar la intrucción: & "C:\Program Files\Git\bin\bash.exe" -c "./docker-compose.sh"
### Paso 4. Presionar la tecla Enter
### Contenido del .sh
```bash
cd msvc-customer-person
mvn clean package -DskipTests
# Verificar si la compilación fue exitosa
if [ $? -ne 0 ]; then
  echo "Error en la compilación. Abortando..."
  exit 1
fi
cd ../msvc-account-movement
mvn clean package -DskipTests
# Verificar si la compilación fue exitosa
if [ $? -ne 0 ]; then
  echo "Error en la compilación. Abortando..."
  exit 1
fi
cd ..

docker-compose build
docker-compose up
```

---

## 🌐 Endpoints Disponibles

| Servicio         | URL                                   | Puerto        |
|------------------|---------------------------------------|---------------|
| api/clientes     | http://localhost:8084/api/clientes    | 8084          |
| /api/cuentas     | http://localhost:8085/api/cuentas     | 8085          |
| /api/movimientos | http://localhost:8085/api/movimientos | 8085          |
| RabbitMQ UI      | http://localhost:15672                | 5672 |

---

## 🐘 PostgreSQL

- **Host:** `localhost`
- **Puerto:** `5432`
- **Usuario:** `postgres`
- **Contraseña:** `postgres`
- **Base de datos:** `banco_db`
- Script inicial cargado: `BaseDatos.sql`

---

## 🔁 Comunicación Asíncrona

Este sistema implementa comunicación asíncrona con RabbitMQ:

- El microservicio `msvc-customer-person` publica eventos al modificar el estado de un `Customer`.
- El microservicio `msvc-account-movement` los consume mediante listeners y actualiza el estado del account.
