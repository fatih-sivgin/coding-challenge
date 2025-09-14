# backend coding-challenge

Here is a short description of the project.

## minimum requirement 

- You need to add lombok.
- You need to setup java-21
- You need maven 3.6.3 at least

## Test

Test the application, so let the unit test and integration tests run.

```shell
mvn -U clean verify
```

## Run

Start the application

```shell
mvn -U clean package
java -jar  ./target/*-SNAPSHOT.jar
```


## Open topics

- Convert entities to persistable entities
- Create spring-data repositories for each entity
- Configure application to let hibernate create db on startup
- Setup global exception handling for bad requests to match to the given api specification


## Links

- [actuator](http://localhost:8080/actuator)
