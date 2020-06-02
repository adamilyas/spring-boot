# Springboot Project
## Spring initializr
[https://start.spring.io/](https://start.spring.io/)
- Spring Web
- Spring Data JPA
- H2 Database
- Spring Boot Actuator

## Database
```
src
    main
        resources
            application.properties
            data.sql
            schema.sql
```

## JpaRepository
[https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.query-creation](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.query-creation)
Used the following methods:
```
List<Object> findByColumnName(String columnName) 
OBJECT findFirstByColumnName(String columnName) 
```
