The guide how to use rest api in the project: 


After running the module : DogBarberShop_rest
You can run one of the following functions/commands
A, To get the list of all services you can use:
curl http://localhost:8084/pa165/rest/services

B, To get a service by id you use:
curl http://localhost:8084/pa165/rest/services/{id}....example http://localhost:8084/pa165/rest/services/1

C, To get a service by name use: 
curl http://localhost:8084/pa165/rest/services/findbyname/{name}... example http://localhost:8084/pa165/rest/services/findbyname/Massage
