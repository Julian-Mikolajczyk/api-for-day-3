Library training project
========================

This project demonstrates persistence in Spring Boot using H2, Spring JDBC (JdbcTemplate), Spring Data JPA, and transaction management.

How to run
----------
- Build: mvn clean package
- Run: mvn spring-boot:run
- H2 console: http://localhost:8080/h2-console
  JDBC URL: jdbc:h2:mem:library

APIs
----
- GET /api/authors
- POST /api/authors
- GET /api/books/jpa
- POST /api/books/jpa
- GET /api/books/jdbc
- POST /api/books/jdbc
- POST /api/books/jpa/loan  -> create book and loan inside a transaction
- POST /api/books/jpa/fail  -> demonstrates rollback
