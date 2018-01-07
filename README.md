# PleasedHeart
Mini implementación de sistema de revisión de puntajes de restaurantes al estilo [https://www.guiaoleo.com.ar/](https://www.guiaoleo.com.ar/).

La aplicación expone una API REST en donde se encuentran controladores con operaciones CRUD de:
1. Clientes Finales (customers).
2. Dueños de Restaurantes (owners) (NOTA: Un dueño puede tener más de un restaurante).
3. Restaurantes (restaurants).
4. Valoraciones (reviews) (Las valoraciones de servicio, comida y ambiente son representadas por un rango de valores de 1 a 30, donde 30 es la mejor valoración, la valoración 0 debe ser interpretada como "No Data").

## Puesta en funcionamiento:
Para hacer funcionar la aplicación se requiere una base de datos MySQL de pruebas.
Inicialmente modificar el archivo application.properties ubicado en src/main/resources para que posea los valores correspondientes.

```yml
spring.datasource.url=jdbc:mysql://localhost:3306/pleasedheart
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.platform=mysql
```
    
NOTA: El schema especificado en la propiedad "url" debe ser creado previamente para el funcionamiento de la API.

Una vez que se tenga acceso a la base de datos se puede proceder con la compilación:
`mvn clean install`

El procedimiento inicial carga datos falsos en la base de datos generados con [https://www.mockaroo.com/](https://www.mockaroo.com/).
En total se generan 20 dueños, 50 restaurantes, 500 clientes y 1000 revisiones. El procedimiento de carga de estos datos puede demorar alrededor de 50 segundos sólo en la primer ejecución.

Iniciar la aplicación:
1. Desde línea de comandos en la carpeta target ejecutar: `java -jar pleasedheart-0.0.1-SNAPSHOT.jar`
2. Desde el IDE seleccionar como clase principal `com.aieme.pleasedheart.PleasedHeartApplication`

## API
La API expuesta se puede consumir desde el navegador para los métodos GET y con un servicio tipo POSTMAN para los métodos POST, PUT y DELETE.
No posee implementación de vistas ni cliente REST.

---
### Operaciones customers
---
> GET List: [http://localhost:8080/customers/](http://localhost:8080/customers/).
> GET One: [http://localhost:8080/customers/{id}](http://localhost:8080/customers/1).
> POST Create: [http://localhost:8080/customers/](http://localhost:8080/customers/).

`Header: Content-Type: application/json`

```json
    {
	    "name": "Juan Perez",
	    "email": "jp@gmail.com",
	    "phone": "22222222"
    }
```
> PUT Update: [http://localhost:8080/customers/{id}](http://localhost:8080/customers/502).

`Header: Content-Type: application/json`

```json
    {
	    "name": "Juan Perez",
	    "email": "jp@gmail.com",
	    "phone": "123123123"
    }
```
> DELETE delete: [http://localhost:8080/customers/{id}](http://localhost:8080/customers/502).

---
### Operaciones owners
---
> GET List: [http://localhost:8080/owners/](http://localhost:8080/owners/).
> GET One: [http://localhost:8080/owners/{id}](http://localhost:8080/owners/1).
> POST Create: [http://localhost:8080/owners/](http://localhost:8080/owners/).

`Header: Content-Type: application/json`

```json
    {
	    "name": "Juan Perez",
	    "email": "jp@gmail.com",
	    "phone": "22222222"
    }
```
> PUT Update: [http://localhost:8080/owners/{id}](http://localhost:8080/owners/21).

`Header: Content-Type: application/json`

```json
    {
	    "name": "Juan Perez",
	    "email": "jp@gmail.com",
	    "phone": "1231231231"
    }
```
> DELETE delete: [http://localhost:8080/owners/{id}](http://localhost:8080/owners/21).

---
### Operaciones restaurants
---
> GET List: [http://localhost:8080/restaurants/](http://localhost:8080/restaurants/).
> GET One: [http://localhost:8080/restaurants/{id}](http://localhost:8080/restaurants/1).
> POST Create: [http://localhost:8080/restaurants/](http://localhost:8080/restaurants/).

`Header: Content-Type: application/json`

```json
    {
	    "name": "La Esquina",
	    "email": "le@gmail.com",
	    "address": "Corrientes 2256",
	    "owner": {
		    "id":21
	        }
    }
```
> PUT Update: [http://localhost:8080/restaurants/{id}](http://localhost:8080/restaurants/51).

`Header: Content-Type: application/json`

```json
    {
	    "name": "La Esquina",
	    "email": "le@gmail.com",
	    "address": "Corrientes 2000",
	    "owner": {
		    "id":21
	        }
    }
```
> DELETE delete: [http://localhost:8080/restaurants/{id}](http://localhost:8080/restaurants/51).
> GET ByOwnerId: [http://localhost:8080/restaurants/owner?id={id}](http://localhost:8080/restaurants/owner?id=1).

---
### Operaciones reviews
---
> GET List: [http://localhost:8080/reviews/](http://localhost:8080/reviews/).
> GET One: [http://localhost:8080/reviews/{id}](http://localhost:8080/reviews/1).
> POST Create: [http://localhost:8080/reviews/](http://localhost:8080/restaurants/).

`Header: Content-Type: application/json`

```json
    {
	    "date": "2018-01-08T11:35:02.000",
	    "customer": {
		    "id":502
	    },
	    "restaurant": {
		    "id":51
	    },
	    "scoreService":27,
	    "scoreFood":20,
       	"scoreEnvironment":29
    }
```
> PUT Update: [http://localhost:8080/reviews/{id}](http://localhost:8080/restaurants/1001).

`Header: Content-Type: application/json`

```json
    {
	    "date": "2018-01-08T11:35:02.000",
	    "customer": {
		    "id":502
	    },
	    "restaurant": {
		    "id":51
	    },
	    "scoreService":20,
	    "scoreFood":21,
       	"scoreEnvironment":29
    }
```
> DELETE delete: [http://localhost:8080/reviews/{id}](http://localhost:8080/reviews/1001).
> GET AllByCustomerId: [http://localhost:8080/reviews/customer?id={id}](http://localhost:8080/reviews/owner?id=1).
> GET AllByRestaurantId: [http://localhost:8080/reviews/restaurant?id={id}](http://localhost:8080/reviews/restaurant?id=1).
> GET TotalAvgByRestaurantId: [http://localhost:8080/reviews/totalavg?restaurantId={id}](http://localhost:8080/reviews/totalavg?restaurantId=1).
> GET TotalAvgByRestaurantIdBetweenDates: [http://localhost:8080/reviews/avg?restaurantId={id}&startDate={yyyy-MM-dd}&endDate={yyyy-MM-dd}](http://localhost:8080/reviews/avg?restaurantId=1&startDate=2018-01-06&endDate=2018-01-07)
`Retorna el promedio de reviews total entre las fechas especificadas para el restaurant requerido`
`Ejemplo: http://localhost:8080/reviews/avg?restaurantId=1&startDate=2018-01-06&endDate=2018-01-07`
> GET EachDayAvgReviewsByRestaurantId: [http://localhost:8080/reviews/daybydayavg?restaurantId={id}&startDate={yyyy-MM-dd}&endDate={yyyy-MM-dd}](http://localhost:8080/reviews/daybydayavg?restaurantId=1&startDate=2017-12-08&endDate=2018-01-07).
`Retorna el promedio de reviews por cada uno de los días entre las fechas especificadas para el restaurant requerido`
`Ejemplo: http://localhost:8080/reviews//daybydayavg?restaurantId=1&startDate=2017-12-08&endDate=2018-01-07`
> Get AllDaysAvgReviewsByRestaurantId: [http://localhost:8080/reviews/alldaysavg?restaurantId={id}](http://localhost:8080/reviews/alldaysavg?restaurantId=1)
`Retorna el promedio de reviews por cada uno de los días que se encuentran cargados en la base de datos para el restaurant requerido`
`Ejemplo: http://localhost:8080/reviews/alldaysavg?restaurantId=1`


