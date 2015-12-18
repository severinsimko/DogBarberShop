The guide how to use rest api in the project: 

In root folder:

mvn clean install

cd DogBarberShop_rest/

mvn tomcat7:run

After running the module

You can run one of the following functions/commands

A, To get the list of all services you can use:

curl -X GET http://localhost:8080/pa165/rest/services

B, To get a service by id you use:

curl -X GET http://localhost:8080/pa165/rest/services/{id}

Example: curl -X GET http://localhost:8080/pa165/rest/services/1

C, To get a service by name use: 

curl -X GET http://localhost:8080/pa165/rest/services/findbyname/{name}

Example curl -X GET http://localhost:8080/pa165/rest/services/findbyname/Massage
