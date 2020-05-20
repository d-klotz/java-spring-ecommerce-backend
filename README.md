<h1 align="center">E-commerce Backend Java Spring Application</h1>

## :electric_plug: Requirements

- Java 8
- Maven
- Docker

# :closed_lock_with_key: Initital Instructions
### Installing MySql Container
In order to run the project, you will need a [MySQl docker container](https://hub.docker.com/_/mysql) running.

```shell
# Download and run mysql
$ docker run -p 33061:3306 --name ecommerce -e MYSQL_ROOT_PASSWORD=root -d mysql:latest
```

If you change MySQL port or root password, you have to change it in the [application.properties](https://github.com/d-klotz/java-spring-ecommerce-backend/blob/master/src/main/resources/application.properties) file.


# :zap: Running the Application
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
