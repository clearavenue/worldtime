FROM maven:3.6.0-jdk-8-alpine AS BUILDER
COPY pom.xml /tmp/
COPY worldtime-pmd.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
RUN mvn clean package -DskipTests

FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY --from=BUILDER /tmp/target/worldtime.jar /worldtime.jar
ENTRYPOINT ["java","-jar", "/worldtime.jar"]
