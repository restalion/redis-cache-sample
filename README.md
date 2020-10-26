# redis-cache-sample project

## TL;RD;

This is a sample project to show how current version of a non-official redis extension it's working. It's based in the cache tutorial from redis so you can find that 90% of the code is the same.

### Previous steps

#### Run a Redis sever locally
You can do this with docker as below:

```bash
docker run --ulimit memlock=-1:-1 -it --rm=true --memory-swappiness=0 --name redis_quarkus_test -p 6379:6379 redis:latest
```

Once Redis is up&running you can start the application.

To see results you can write the URL below in a REST client:

http://localhost:8080/weather?city=R'lyeh

```json
{
    "dailyForecasts": [
        "SUNDAY will be cloudy in R'lyeh",
        "MONDAY will be chilly in R'lyeh",
        "TUESDAY will be rainy in R'lyeh"
    ],
    "executionTimeInMs": 6036
}
```

The first time it should take some time to answer, but the second one should take less.

```json
{
    "dailyForecasts": [
        "SUNDAY will be cloudy in R'lyeh",
        "MONDAY will be chilly in R'lyeh",
        "TUESDAY will be rainy in R'lyeh"
    ],
    "executionTimeInMs": 9
}
```

In Redis you should see the generated keys for these caches.

```bash
127.0.0.1:6379> keys *
1) "R'lyeh2020-10-27"
2) "R'lyeh2020-10-25"
3) "R'lyeh2020-10-26"
```

## About the Quarkus framework

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

### Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev
```

### Packaging and running the application

The application can be packaged using `./mvnw package`.
It produces the `spring-cache-quickstart-1.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/spring-cache-quickstart-1.0-SNAPSHOT-runner.jar`.

### Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/spring-cache-quickstart-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.
