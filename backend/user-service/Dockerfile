# Build
FROM maven:3.9-eclipse-temurin-17-alpine AS build
WORKDIR /app

COPY . .

RUN mvn clean package -pl user-service -am -DskipTests

#Run
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

COPY --from=build /app/user-service/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]