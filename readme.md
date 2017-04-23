# Movie Parental Control Rating Application
Spring boot application that exposes a REST api to find movie parental rating.

End point Spec:
http://{host}:8080/movie/parentalcontrol/{movieId}/{Parental Control level preference}

Example:
http://localhost:8080/movie/parentalcontrol/id2/U

#### Movie rating stub setup

| Movie Id  | Parental Control level  |
|---|---|
| id1  | U  |
| id2  | PG  |
| id3  | 12  |
| id4  | 15  |
| id5  | 18  |
| id6  | System error  |

#### Software requirements...
- Git client (optional if not checking out from github)
- Java 8
- Maven

####  how to Run App...

##### Use one of the option below to run

- mvn spring-boot:run
- mvn package && java -jar target/parental-control.jar
- Run the ParentalControlApplication.java from IDE

####  how to Run Tests...
- mvn test