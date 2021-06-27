# JavaEE dummy REST API

Dummy rest-api using following technology stack:

- JakartaEE 8
- Open API
- Wildfly 24
- Docker

## Run Application 

There are three ways to deploy the app.

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

For development I would use `mvn wildfly-war:dev-watch` because changes will be monitored and deployed fully automatically.

### Deploy on webserver

You could just copy the `target/dummy-rest-1.0.war` to you appserver autodeployment folder.

### Docker

```cmd
docker build -t javaee-rest .
docker run -d -p 8080:8080 javaee-rest
```
