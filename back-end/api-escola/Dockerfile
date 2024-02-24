# Estágio 1: Build do JAR
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY ./ /app/
RUN mvn package -Dmaven.test.skip

# Estágio 2: Criação da imagem final
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/api-escola.jar /app/main.jar
EXPOSE 8080
CMD ["java", "-jar", "main.jar"]