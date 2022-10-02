# Online Cab Booking Application



## ER diagram

![CW_erd-Page-1 drawio](https://user-images.githubusercontent.com/91946820/193461776-93cf301d-6720-41d5-b879-2f65c6eda855.png)


## Service Layer



## Tech Stack
- Java
- Spring Framework
- Spring Boot
- Spring Data JPA
- Hibernate
- Spring Validation
- MySQL
- Lambok
- Swagger Ui

## Modules
- Login Module
- Admin Module
- Customer Module
- Driver Management Module
- Cab Management Module
- Trip Booking Management Module

## Features

-
-



## Installation and Run

```
#changing the server port
server.port=8088

#db specific properties
spring.datasource.url=jdbc:mysql://localhost:3306/cabbooking
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=root


#ORM s/w specific properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

#No handler found
spring.mvc.throw-exception-if-no-handler-found=true
spring.web.resources.add-mappings=false

#swagger-ui
spring.mvc.pathmatch.matching-strategy = ANT_PATH_MATCHER

```

## API Root and Endpoint

```
https://localhost:8088/
```

```
http://localhost:8088/swagger-ui/
```

## Contributors

- [Soumyakant Swain](https://github.com/Soumya048)
- [Melbin Zacharia](https://github.com/melbinzaharia)
- [Washim Raza](https://github.com/washimraza1234)
- [Juli Kumari](https://github.com/Julikumari048)
- [Nitin](https://github.com/nitinaggarwal2512)




