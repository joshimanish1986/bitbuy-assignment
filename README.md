# bitbuy-assignment

# Pre-requisite: JDK8+ and maven should be installed in machine

Download the code

Using commnd line, in the folder where the pom.xml file exists, run command :-  mvn clean install

From target folder run command :-  java -jar bitbuy-0.0.1-SNAPSHOT.jar

For positive test cases, please use the below API endpoints and sample request to test in the given order using Postman:-

# a) POST : http://localhost:8080/api/create
Request Body -
{
    "userName": "testname",
    "password": "pass100"

} 

Expected Response: Status 201 created and the created user returned in the Response Body

# b) POST : http://localhost:8080/api/login
{
    "userName": "testname",
    "password": "pass100"

} 

Expected Response: Status 200 OK and the message "User login success" in the Response Body

# c) GET: http://localhost:8080/api/users/1
In the Autharization -> Type Basic Auth, enter:-

Username: bitbuy
Password: Crypto$123

Under Headers, provide this key value
userName: testname

Expected Response: Status 200 OK and the message user details in the Response Body

# d) POST : http://localhost:8080/api/users/1
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
