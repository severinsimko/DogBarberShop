The guide how to use rest api in the project: 

In root folder:

-----------------------

mvn clean install

cd DogBarberShop_rest/

mvn tomcat7:run

-----------------------------

After running the module

You can run one of the following functions/commands

-------------------------------------------------

A, To get the list of all services you can use:

curl -X GET http://localhost:8080/pa165/rest/services

----------------------------------------------------

B, To get a service by id you use:

curl -X GET http://localhost:8080/pa165/rest/services/{id}

Example: curl -X GET http://localhost:8080/pa165/rest/services/1

-------------------------------------------------------------------

C, To get a service by name use: 

curl -X GET http://localhost:8080/pa165/rest/services/findbyname/{name}

Example curl -X GET http://localhost:8080/pa165/rest/services/findbyname/Massage

---------------------------------------------------------------------------------

D, To create a new service use:

curl -i -X POST -H "Content-Type: application/json" --data '{"serviceName":"new_service_name","lengthInMinutes":"new_service_length","price":"new_service_price"}' http://localhost:8080/pa165/rest/services/create

Example: curl -i -X POST -H "Content-Type: application/json" --data '{"serviceName":"SOME_NEW_SERVICE","lengthInMinutes":"120","price":"10"}' http://localhost:8080/pa165/rest/services/create

--------------------------------------------------------------------------------------------------------------------------

E, To update the service use: 

curl -i -X PUT -H "Content-Type: application/json" --data '{"serviceName":"name","lengthInMinutes":"length","price":"price"}' http://localhost:8080/pa165/rest/services/1

Example: curl -i -X PUT -H "Content-Type: application/json" --data '{"serviceName":"New Name","lengthInMinutes":"2000","price":"5"}' http://localhost:8080/pa165/rest/services/1


---------------------------------------------------------------------------------------------

F, To delete the service use:

curl -i -X DELETE http://localhost:8080/pa165/rest/services/{id}

Example: curl -i -X DELETE http://localhost:8080/pa165/rest/services/4




