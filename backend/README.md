#  coding-challenge backend

Here is a short description of the project.

## minimum requirement 

- You need to add lombok.
- You need to setup java-21
- You need maven 3.6.3 at least

## Test

Test the application.

```shell
mvn -U clean package
```

## Run

Start the application

```shell
mvn -U clean package
java -jar  ./target/*-SNAPSHOT.jar
```


## Open topics

- GET /api/v1/trainings/{id} is missing in api-design, here required since created api delivers location back
- Setup global exception handling for bad requests to match to the given api specification
- Setup security with an authorization-service providing JWT
- Set frontend url as a config in application.properties
- Setup logging

## Links

- [actuator](http://localhost:8080/actuator)
- [implementation](http://localhost:8080/api/v1/trainings)
