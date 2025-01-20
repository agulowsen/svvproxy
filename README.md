# Statens Vegvesen Proxy
A small Spring boot application that exposes some SVV endpoints to test Docker And Kubernetes on different cloud service providers.


```
Health can be verified on /actuator/health  Should return 200 and "OK"
```

## Swagger
#### Swagger UI: /swagger-ui/index.html

## Installation


### Application properties variables
The application uses the following variables from the properties file

| Variable  | Value                                       | Note |
|-----------|---------------------------------------------| ------------- |
| svv_token | Token to access SVV vehicle information API | Free 

#### Get API Token for vehicle information
https://www.vegvesen.no/om-oss/om-organisasjonen/apne-data/et-utvalg-apne-data/api-for-tekniske-kjoretoyopplysninger/

### Build application
Application is built on Java 17
```
mvn clean install
```

### Run application
```
mvn spring-boot:run
```


## Docker
### Build Dockerfile
```
cd into project 
docker build svv-proxy .
```

### Run and expose port 8080 outside container
Requires svv_token set in application.properties
```
docker run -p 8080:8080 svv-proxy
```

### Run with dev profile
Requires svv_token set in application-dev.properties
```
docker run -p 8080:8080 -e "-SPRING_PROFILES_ACTIVE=dev" svv-proxy
```
