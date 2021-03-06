FROM maven:3.6.0-jdk-8-alpine AS build
WORKDIR /opt
COPY src/ ./src
COPY pom.xml ./
RUN mvn -B -DskipTests clean package

FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY --from=build /opt/target/*.jar ./peeper.jar
ENTRYPOINT ["java","-jar","peeper.jar"]
