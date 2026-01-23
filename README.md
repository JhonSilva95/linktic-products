# Linktic Products

Este componente gestiona la persistencia y recuperación de datos de la tabla products. Expone tres endpoints principales:

- POST /create : Gestiona la creación e inserción de nuevos registros en la base de datos.

- GET /product/{id} : Recupera la información detallada de un producto específico mediante su identificador único (ID).

- GET /find-all-products: Realiza una consulta global para listar la totalidad de los productos almacenados.

A diferencia de los servicios de infraestructura pura, este componente está diseñado para interactuar directamente con el usuario final. Actúa como el motor de cara al cliente, gestionando la lógica de compra y la validación de disponibilidad con un enfoque de experiencia de usuario.

## Tecnologías Principales
 Las tecnologías principales usadas en este proyecto son las siguiente:
 - Java 21
 - Springboot 4.0.1
 - Lombok
 - Mapstruct

## Ejecución

Para poder ejecutar este proyecto de manera local puede hacerlo de la siguiente manera:
```bash
mvn clean package
```
```bash
mvn spring-boot:run
```

Ahora bien si lo deseas ejecutar en un contendor docker realizalo de la siguiente mandera:

#### Para construir el contenedor.
recuarda estar al mismo nivel del Dockerfile.
```bash
docker build -t product-service:1.0.0 .
```
#### Para correr el contenedor.
recuarda estar al mismo nivel del Docker file.
```bash
docker run -d -p 8080:8080 --name product-app -e JAVA_OPTS="-Xms256m -Xmx512m" product-service:1.0.0
```

### Ejecutar todo con base de datos incluido - docker-compose:
```bash
docker-compose up -d
```
ver logs:
```bash
docker-compose logs -f product-service
```
apagar todo
```bash
docker-compose down
```
## Uso
A continuación las curl y su respuesta para tener en cuenta como debería funcionar el componente:

#### Crear Producto
```curl
request POST 'http://localhost:8080/products/create' \
  --header 'Content-Type: application/json' \
  --body '{
    "name":"Laptop hp",
    "price":1500000,
    "description":"Computadora gamer marca hp"
}'
```
```json
{
    "code": 0,
    "description": "OK"
}
```
#### Buscar producto por ID
```curl
curl --location 'http://localhost:8080/products/create' \
--header 'Content-Type: application/json' \
--data '{
    "name":"Laptop hp",
    "price":1500000,
    "description":"Computadora gamer marca hp"
}'
```
```json
{
    "code": 0,
    "content": {
        "id": 1,
        "name": "Arroz",
        "price": 80000,
        "description": "Arroba de arroz Diana"
    },
    "description": "OK"
}
```
#### Buscar todos los productos
```curl
curl --location 'http://localhost:8080/products/find-all-products'
```
```json
{
    "code": 0,
    "content": [
        {
            "id": 1,
            "name": "Arroz",
            "price": 80000,
            "description": "Arroba de arroz Diana"
        },
        {
            "id": 3,
            "name": "Laptop",
            "price": 1500000,
            "description": "Computadora gamer"
        },
        {
            "id": 4,
            "name": "tablet",
            "price": 1300000,
            "description": "Tablet Galaxy"
        },
        {
            "id": 5,
            "name": "Laptop hp",
            "price": 1500000,
            "description": "Computadora gamer marca hp"
        }
    ],
    "description": "OK"
}
```
A continuación te muestro los códigos de respuesta que el componte responde:

- Codigo 1 descripción OK : significa que cualquier flujo realizado fue un exito.
- Codigo 2 descripción GENERAL ERROR PRODUCTS: Algun error ocurrido en el transcurso de algún flujo.
- Código 3 descripción ERROR SAVING THE PRODUCT: Error a la hora de guardar el producto.
- Código 4 descripción PRODUCTS NOT FOUNDS: productos no encontrados.

## Nota
Para este componente se utilizo la ayuda de la IA Gemini para ayuda mas que todo en los test ya que los genera de manera rapido y fácil de entender.

## Autor
Jhon Alexander Silva - Desarrollador de software.