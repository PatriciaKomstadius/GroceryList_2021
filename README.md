##GroceryList Application -
####Schoolproject Complex Java-programming HT2021

- This application provides functionality to create an account at a store and to add, edit, update and delete grocerylists. 
- Includes functionality to manage users, products, layouts, departments, and company information. Basic auth as admin or employee is needed to manage and making changes to these endpoints. 
- The application includes jms listener and exception handling with exception mappers for methods. The ThymeleafController has no exception handling for its methods. 
- Includes a basic data sample used as database for testing purpose only.

####To install the application:
- Download zip/clone project
- Start the application with an IDE

####Enter website:
Login as user, admin or employee to make CRUD operations to your grocerylist and to overview all grocerylists:

> To enter your account go to: http://localhost:8080/application
> 
> To sign up for an account go to: http://localhost:8080/signup


----OBS VAD ÄR RÄTT LÄNK?!
>Overview of all grocerylists: http://localhost:8080/application/getall


####Instructions to test endpoints in Insomnia:

###USERS

*****Create new user:*****   
POST http:localhost:8080/users/signup

Post body in JSON:
```
{
	"username" :"user",
	"email": "newuser@grocerylists.com",
	"firstName": "Bob",
	"lastName": "Anka",
	"password": "user"
}
```

Autentication as admin needed to make GET/PATCH/DELETE requests:
``` Basic auth: 
Username: admin 
Password: 123
```
*****Get all users:*****  
GET http://localhost:8080/users

*****Get one user by id:*****  
GET http://localhost:8080/users/{id}

*****Update users email by id:*****  
PATCH http://localhost:8080/users/{id}

Post new email address in body as json:
```
{
	"email": "usersnewemail@grocerylists.com"
}
```

*****Delete user:*****  
DELETE http://localhost:8080/users/{id} 

_Attention: Delete user only works if the user has no stored grocerylists, otherwise you have to remove the grocerylists before removing the user._


##GROCERYLIST
*****Create new grocerylist:*****  
POST Https://localhost:8080/grocerylists

Basic authentication needed as customer, employee or admin.
``` Basic auth:
Username: customer
Password: 123
```
Post body in json:
```
{
	"name": "foodlist"
}
```

*****Get lists of grocerylists:*****  
GET http:localhost:8080/grocerylists

*****Get grocerylist by id:*****  
GET http:localhost:8080/grocerylists/{id}

*****Update name of grocerylist by id:*****  
PATCH http:localhost:8080/grocerylists{id}

Post new name of grocerylist in body in json:
```
{
	"name" : "teknik"
}
```

*****Delete grocerylist by id:*****  
DELETE http:localhost:8080/grocerylists/{id}  




##ADD PRODUCTS TO GROCERYLIST

*****Add product to existing grocerylist:*****  
POST http://localhost:8080/grocerylists/addproduct/{grocerylist}/{product}

-----> PATCH

http://localhost:8080/grocerylists/addproduct/{grocerylist}/{product}

-----> DELETE product from grocerylist?




##PRODUCTS, DEPARTMENTS, LAYOUTS, COMPANIES..
For these operations autentication as admin is needed:
```Basic auth:
Username: admin 
Password: 123
```

##PRODUCTS
*****Add product to store:*****  
POST http://localhost:8080/addproducts

POST body in json:
```
{
		"productName": "Salt",
		"price": 16.0,
		"category": "Kryddor",
		"quantity": 500
}
```

*****Get all products:*****  
GET http://localhost:8080/products

*****Update price of product by product id:*****  
PATCH http://localhost:8080/products/{id}

Post new price in body in json:
```
{
		"price": "20.0"
}
```

*****Delete product:*****  
DELETE http://localhost:8080/products/{id}



##DEPARTMENTS

*****Create new department:*****  
POST http://localhost:8080/departments

Post department name in body in json:
```
{
"departmentName" : "Rawfood"
}
```

*****Get all departments:*****  
GET http://localhost:8080/departments

*****Update name of department by id:*****   
PATCH http://localhost:8080/departments/{id}

Post new department name in body in json:
```
{
"departmentName" : "Kalas"
}
```

*****Delete department by department id:*****   
DELETE http://localhost:8080/departments/{id}



##LAYOUTS

*****Create new layout:*****   
POST http://localhost:8080/layouts

Post body in json:
```
{
	"type": "Kvantum"
}
```

*****Get all layouts:***** 
GET http://localhost:8080/layouts

*****Update name of layout by id:*****   
PATCH http://localhost:8080/layouts/{id}

Post body in json:
```
{
	"type": "Kvantum"
}
```
*****Delete layout by layout id:*****   
DELETE http://localhost:8080/layouts/{id}



##COMPANIES

*****Create a new company:*****   
POST http://localhost:8080/companies

Post body in json:
```
{
"companyName" : "Ica"

}
```
*****Get all companies:*****   
GET http://localhost:8080/companies

*****Update name of company:*****   
PATCH http://localhost:8080/companies/{id}

Post body in json:
```
{
"companyName" : "Ica"

}
```

*****Delete company by company id:*****   
DELETE http://localhost:8080/companies/{id}

_____________________________________________________________________________________________


##Installation to run the application as a docker container with the JMS Listener:
To use JMS the application needs to run as a docker container. 

- Download zip/clone project to your computer
- Start a container with ActiveMQ Artemis och build needed images below to use the sender and receiver of the application.

Start a docker Container with ActiveMQ Artemis:
>docker container run --name artemis -p 8161:8161 -p 61616:61616 vromero/activemq-artemis

Open terminal where the receiver is found and run following command:
>docker image build -t jms-211206-receiver .

Open terminal where the sender is found and run following command:
>docker image build -t jms-211206-sender .

Start the sender and receiver with Artemis:
>docker container run -p 8080:8080 --link artemis:artemis jms-211206-receiver
>docker container run -p 8081:8080 --link artemis:artemis jms-211206-sender

The application should now send a message every time a customer log in to the application.




