ATM System Using Springboot

Requirement :

Java JDK 17 Maven PostgreSQL Database Spring Boot JPA Step :

Clean Project Terlebih dahulu menggunakan command 'mvn clean install' tanpa tanda kutip. 
Import Data untuk Database yang ada di folder 'db' menggunakan liquibase. 
Run Project menggunakan command 'mvn spring-boot:run' tanpa tanda kutip. 
Untuk mengetahui Coverage Code nya bisa menggunakan command 'mvn jacoco:report' setelah itu bisa dilihat di folder 'target/site/jacoco/index.html'
Testing bisa dilakukan via Swagger via link 'localhost:7878/rsvp/api/swagger-ui/index.html'. 
Terdapat juga Unit Testing pada package '/src/test/java'. Testing bisa juga dilakukan menggunakan Postman Tools curl berikut:

//Add Concert
curl --location 'localhost:7878/rsvp/api/concert' \
--header 'Content-Type: application/json' \
--data '{
"concertName": "Concert Test",
"concertDate": "2024-02-27T21:00:00"
}'

//Get Concert
curl --location 'localhost:7878/rsvp/api/concert'

//Booking Concert
curl --location 'localhost:7878/rsvp/api/booking' \
--header 'Content-Type: application/json' \
--data '{
"concertId": "d36d80b1-f713-4b62-be4b-5e6e6d7d84d3",
"bookingDate": "2024-02-28T21:19:03",
"bookingQty": "6"
}'

Happy Coding.

Created By: Denny Afrizal