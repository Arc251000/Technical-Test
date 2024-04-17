Instrcciones para ejecutar el ecosistema:
  1- Descargar el proyecto
  2- Configurar las variables de entorno del config server con sus credencial de github
  3- Ejecutar los microservicios en el orden:
      - config-server
      - registry-service
      - ms-waste-manager y ms-waste-manager-address
      - ms-gateway

Analisis y desiciones tomadas de las tareas:
  Modelo Entidad Relación:
    Dada que no se contaba con información sobre el conexto de la problemática se deicidio:
      - Se tradujeron los atributos nombre y direccion a name y address para que encajen con el resto de atributos, clases y metodos
      - Se cambio el tipo Date de los atributos createdAt y lastUpdate a LocalDate puesto que en la versión de java utilizada la
      clase Date esta obsoleta
      - Tomar la relación  entre WasteManagerEntity y WasteManagerAddressEntity como una relación de uno a uno y dejar el lado fuerte de esta
      en WasteManagerENtity dado que es el microservicio al que se estar accediendo con mas frecuencia
      - Tomar el WasteCenterAuthorizationEntity como un atributo multivaluado del WasteManagerEntity dado que no se requería un 
      microservicio asociado a esta entidad y para facilitar la ejecución de la aplicación, ademas se mantuvo como una tabla de
      base de datos por razones de escalabilidad, puesto que spring data jpa por defecto almacena los atributos multivaluados
      como valores binarios lo cual dificulta la utilización de librerías especializadas en filtrados como CriteriaAPI  

Validaciones:
  - Dada la escaces de contexto se decidió solo validar de forma general la entrada de los datos referentes a createdAt y lastUpdate puesto que estos son datos
   que el usuario no deberia ser quien inserte estos datos si no el mismo microservicio tomar la fecha en que se realiza la transacción.


Endpoints:
  Si ejecuta los microservicios en su local los enpoints serían:
   -Obtener por WasteManagerEntity id:
    url: http://localhost:8080/waste-manager/{id}
    Metodo: GET
    body example: no admite
    response: {
        	"id": 2,
        	"name": null,
        	"nif": null,
        	"wasteManagerAddress": "hola2",
        	"listOfWasteCenterAuthorization": [
        		{
        			"authorizationNumber": "5"
        		},
        		{
        			"authorizationNumber": "7"
        		}
        	],
        	"isEnabled": true,
        	"createdDate": "2024-04-17",
        	"lastModifiedDate": "2024-04-17"
        }
    descripción:  este endpoint devuelve el WasteManagerEntity asociado a dicho id o noContent en caso de que no exista
    

  - Crear WasteManagerEntity:
    url: http://localhost:8080/waste-manager/create
    Metodo: POST
    body example: {
          	"name":"hgdfh",
          	"nif":"hfd",
          	"wasteManagerAddress": "hola",
          	"listOfWasteCenterAuthorization":[
          		{
          			"authorizationNumber": "5"
          		},
          		{
          			"authorizationNumber": "7"
          		}
          	]
          }
    descripción:  este endpoint crea un WasteManagerEntity, admite cualquier atributo del diseño recibido exepto las fechas y el id y devuelvve el id del objeto creado, en el diseño recibido este endpoint no devuelve nada, sin embargo, se modifico puesto que es común que un cliente al crear una entidad necesite recibir el id para funcionar de forma óptima.
    response example: 2

 - Actualizar WasteManagerEntity:
    url: http://localhost:8080/waste-manager/update
    Metodo: PUT
    body example: {
            "id":2
          	"name":"hgdfh",
          	"nif":"hfd",
          	"wasteManagerAddress": "hola",
          	"listOfWasteCenterAuthorization":[
          		{
          			"authorizationNumber": "5"
          		},
          		{
          			"authorizationNumber": "7"
          		}
          	]
          }
    descripción:  este endpoint actualiza un WasteManagerEntity,requiere el id de la entidad, admite cualquier atributo del
    diseño recibido exepto las fechas
    response example: none
  
