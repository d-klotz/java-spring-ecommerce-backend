<h1 align="center">E-commerce Backend Java Spring Application</h1>

## :electric_plug: Requirements

- Java 8
- Maven
- Docker

# :closed_lock_with_key: Initital Instructions

First get all the requirements installed on your system.
In order to run the project, you will need a [MySQl docker container](https://hub.docker.com/_/mysql) running.
After having it running, include the Mysql connection url, user and password in the [application.properties](https://github.com/d-klotz/java-spring-ecommerce-backend/blob/master/src/main/resources/application.properties) file.



### Getting started the API Restful backend

Clone this repository and install all dependencies.

```shell
# Install all dependencies using Maven
$ mvn install
```

Start the project running the file EcommerceApplication.java. Or if you prefer, you can run it on docker:

```shell
$ docker run -p 8080:8080 daklotz/ecommerce:0.0.1-SNAPSHOT
```

<hr />

### <a href="http://linkedin.com/in/danielfelipeklotz">Contact me on LinkedIn</a>
