FROM maven:3.6-jdk-11-slim AS builder

COPY . /build/.
WORKDIR /build

RUN mvn clean verify


FROM openjdk:11.0.4-slim AS service

EXPOSE 8080
COPY --from=builder /build/target/dummy-rest-1.0-bootable.jar .

ENV JAVA_OPTS=""
ENV WF_OPTS="-b=0.0.0.0 -bmanagement=0.0.0.0"

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar dummy-rest-1.0-bootable.jar $WF_OPTS" ]
