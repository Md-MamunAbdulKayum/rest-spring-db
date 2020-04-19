# rest-spring-db
TO run the system and see the output we need to follow the following steps:

## Server:
------------------
1. We have to create a dadabase called "userest" then import userrest.sql file at MySql server
2. Run the server app(RestSpringServer) as "Run on server" at eclipse
3. You can hit the following url at browser :
http://localhost:8080/SpringRestServer/service/user/1   
-- (it only works if you are running at port 8080)

This will provide the user info with id 1.


## Client 

To test with the client application, you must run the server first like mention above , then

1. Run the client app(RestSpringClient)  as "Run on server" at eclipse
2. Hit
<br>a. http://localhost:8080/RestSpringClient/getuser to get a particular user, internally calls rest service  http://localhost:8080/SpringRestServer/service/user/{id}

### Response: 
Get particular user service result: 
<200 OK,userName=Dada, userId=1, age=29, salary=1.0E7, address=Test address,{Server=[Apache-Coyote/1.1], Content-Type=[application/json;charset=UTF-8], Transfer-Encoding=[chunked], Date=[Thu, 25 Oct 2018 18:05:06 GMT]}>

![getuser](https://github.com/Md-MamunAbdulKayum/rest-spring-db/blob/master/getuser.JPG)


b. http://localhost:8080/RestSpringClient/getuserlist to get all users in the db table, internally calls rest service
http://localhost:8080/SpringRestServer/service/user

![getuserlist](https://github.com/Md-MamunAbdulKayum/rest-spring-db/blob/master/getuserlist.JPG)


c. http://localhost:8080/RestSpringClient/createuser to create/insert a user, internally calls rest service
http://localhost:8080/SpringRestServer/service/user/create

d. http://localhost:8080/RestSpringClient/deleteuser to delete a particular user, internally calls rest service
http://localhost:8080/SpringRestServer/service/user/delete/{id}

e. http://localhost:8080/RestSpringClient/updateuser to update a user, internally calls rest service
http://localhost:8080/SpringRestServer/service/user/update/{id}



## Further Improvement areas:
1. Add custom validation
2. Add logging (Log4j)
3. Organize code and make everything configurable 
4. Make UI for client application to get input and show the response from rest service calling 





