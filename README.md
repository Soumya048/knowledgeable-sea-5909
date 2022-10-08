# Online Cab Booking Application                                                          


REST API for an Online Cab Booking Application. Customer can book any type of cab to traveling different destinations. This API performs all the fundamental CRUD operations. There are validations for input data. and usersession uuid to access the API.

## Badges

![Badge](https://visitor-counter-badge.vercel.app/api/Soumya048/knowledgeable-sea-5909/)

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

## ER Diagram

![CW_erd-Page-1 drawio](https://user-images.githubusercontent.com/91946820/193461776-93cf301d-6720-41d5-b879-2f65c6eda855.png)


## Service Interface

![CW_erd-Page-2](https://user-images.githubusercontent.com/91946820/193463654-153d1ef7-6a4f-45eb-9651-3da6cf4ee6cf.jpg)



## Features

- Customer, Driver and Admin authentication & validation with session UUID.
- Admin Features:
  - Admin can register, log in, log out, update and delete accounts
  - Admin have control over the entire application
  - Admin can manage Cab, Driver, and Booking
  - Admin can access the details of different customers, drivers and trip bookings
  - Only logged-in Admin can access all features of Admin
- Customer Features:
  - Customers can register themselves with the application, logging in and logout into the application
  - Customers can book cabs for the desired location from a specific location and can cancel the booked trip
  - Customers can update, or delete their accounts
  - Only logged-in users can access all customer features
- Driver Features:
  - Drivers can register themselves with the application, log in and log out into the application
  - Driver can update the trip status
  - Drivers can update and delete their accounts
  - Only logged-in drivers can access all driver features

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



