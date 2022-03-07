# JavaEE dummy REST API

[![Docker Image CI](https://github.com/saschakr/dummy-rest/actions/workflows/docker-push.yml/badge.svg)](https://github.com/saschakr/dummy-rest/actions/workflows/docker-push.yml)

Dummy rest-api using following technology stack:

- JakartaEE 9.1.0
- Open API
- Wildfly 26
- Docker

## Run Application 

There are three ways to deploy the app.

### Docker

You can simply run this container with: 

```cmd
docker pull ghcr.io/saschakr/dummy-rest:latest
docker run -p 8080:8080 ghcr.io/saschakr/dummy-rest:latest
```

or even build it local with:

```cmd
docker build -t dummy-rest .
docker run -d -p 8080:8080 dummy-rest
```

### Maven 

Use the Wildfly bootable-jar cli commands.

```cmd
# build app
mvn clean install
# start app
mvn wildfly-jar:[dev|run|start|shutdown]
```

| Parameter | Description                | 
| --------- | -------------------------- |
| dev       | Run appserver in dev mode. |
| run       | Run blocking appserver     |
| start     | run non blocking appserver | 
| shutdown  | kill running appserver     |

More informations see documentation: https://docs.wildfly.org/bootablejar/#wildfly_jar_other_goals

For development I would use `mvn wildfly-jar:dev-watch` because changes will be monitored and deployed fully automatically.

### Deploy on webserver

You could just copy the `target/dummy-rest-1.0.war` to you appserver autodeployment folder.

### Start via `java`

You could just run `java -jar target/dummy-rest-bootable.jar`
