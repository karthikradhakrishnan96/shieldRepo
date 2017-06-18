# Shield


Shield is a secure architecture for secure co-ordination and datasharing between Avengers and XMen websites based on encrypted JWT tokens for authorization. Shield implements Single-Sign on to seamlessly switch between Avengers and XMen websites based on iframe message passing through CORS and verification of active sessions. Shield is implemented as three servers, a database server and two servers running on Avengers and Xmen domains respectively.

# Features!

  - Secured with JWT tokens. Each API call verifies the JWT provided with the central database for authenticity
  - Secure Single SignOn between the two websites with two-step verification 
  - Backend for secure password storage base do on BCrypt hashes
  - Sanitization of inputs to prevent network attacks
  - All APIs are documented using Swagger for easy access

Functional Requirements Covered:
  - To-Do lists : A user can add new tasks, view tasks from their squad(s) and mark tasks as closed. Tasks can be marked as private or made accessible to their squad.
  - Bio : A user can add / change their bio on either of the website as well as view the bio updates of their squad. The HTML is cleaned to strip off all scripts and rendered cleanly on the dashboard
  - Single logout for both websites
  - Seamless and secure login between two websites

### Architecture

![pic](https://github.com/karthikradhakrishnan96/shieldRepo/blob/master/marvel-architecture.png)

DBServer : Stores the Database of users, toDos, bios, and active tokens. Exposes REST APIs to edit the database which is consumed only by the AVENGERS and XMEN servers. 

AVENGERS and XMEN Servesr : Serves the HTMLs for login and dashboard and exposes REST APIs to perform CRUD operations secured with JWT. These Servers take the requests from client, verify, clean and calls the DBServer for making the updates. 

### Setup and Instalation

Clone this Repositoy 
```sh
    git clone https://github.com/karthikradhakrishnan96/shieldRepo.git
```
This Repository contains three maven Projects, Avengers, XMen, and DBServer. Import them to Idea and run the Application to start the servers

Swagger documentation provided for the three servers
AVENGERS
```sh
    http://127.0.0.1:9000/swagger-ui.html
```
XMEN
```sh
    http://localhost:9001/swagger-ui.html
```
DBServer
```sh
    http://localhost:6969/swagger-ui.html
```

The Avengers server runs on http://127.0.0.1:9000 and the XMEN server runs on http://localhost:9001 . (Have used localhost and 127.0.0.1 just to simulate a real situation where the two servers are on different domains)

### Tech Stack

  - JAVA 8, SpringBoot 1.5.4, Maven 
  - BCrypt for password hashing 
  - JWT for encrypted tokens using Java JWT Library
  - Swagger for API Documentation
  - All APIs are documented using Swagger for easy access
