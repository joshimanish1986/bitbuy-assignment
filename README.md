# bitbuy-assignment
Download the code
Run mvn clean install in the folder with the pom.xml file
For positive test cases, please use the below API endpoints and sample request to test in the given order using Postman:-
a) POST : http://localhost:8080/api/create
Request Body -
{
    "userName": "testname",
    "password": "pass100"

} 
Expected Response: Status 201 created and the created user returned in the Response Body

b) POST : http://localhost:8080/api/login
{
    "userName": "testname",
    "password": "pass100"

} 
Expected Response: Status 200 OK and the message "User login success" in the Response Body

c) GET: http://localhost:8080/api/users/1
In the Autharization -> Type Basic Auth 
Username: bitbuy
Password: Crypto$123
Expected Response: Status 200 OK and the message user details in the Response Body

c) POST : http://localhost:8080/api/users/1
In the Autharization -> Type Basic Auth 
Username: bitbuy
Password: Crypto$123
{
    "userName" : "testname",
    "password" : "pass100",
    "name": "Manish",
    "email": "bitbuy@test.com",
    "phone" : "6147890235"
}
Expected Response: Status 200 OK and the message user details in the Response Body
