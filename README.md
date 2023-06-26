# Reward management
This Java application provides an API to retrieve information about customer bonus points, manage customers and transactions.

### Technologies Used

    Java 17
    Spring Boot 3
    Maven

### Running the Application

To run the application, clone the repository and run the following command:

    mvn spring-boot:run

By default, the application runs on port 8080. 

### Swagger-ui

    http://localhost:8080/reward-management/swagger-ui/index.html

### h2-console

    http://localhost:8080/reward-management/h2-console 
credentials: username/password


### Docker
Make sure that you are in github-proxy catalog (at the same level as the dockerfile)
1. docker build --tag=reward-management:latest . or add a tag you like
2. docker run -p8887:8080 reward-management:latest

then you can call api

    localhost:8887/*
    