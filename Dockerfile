FROM maven:3-openjdk-17-slim AS builder

COPY . /build/.
WORKDIR /build

RUN mvn clean verify


FROM openjdk:17-jdk-slim AS service

EXPOSE 8080
COPY --from=builder /build/target/dummy-rest-bootable.jar .

ENV JAVA_OPTS=""
ENV WF_OPTS="-b=0.0.0.0 -bmanagement=0.0.0.0"

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar dummy-rest-bootable.jar $WF_OPTS" ]
