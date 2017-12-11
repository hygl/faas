# faas
minimal function as a service stack (serverless) based on docker, redis and rabbitmq.

## Create a docker container

You can now build, package and run this microservice using Docker.

Now you can build your docker image by entering from a terminal where
you have access to Docker, execute the following command:

```sh
$ ./mvnw clean package docker:build
```

Even push it to a repository of your choice:

```sh
$ ./mvnw clean package docker:build -DpushImage
```
## Test

At the moment the tests need a running redis daemon. 

```sh
docker run -d -p 6379:6379 redis:3.2.5-alpine
```

I am trying to solve this programmatically in the future. 
