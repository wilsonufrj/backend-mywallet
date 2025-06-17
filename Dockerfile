# Etapa 1: Build da aplicação
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Criar a imagem final
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/mywallet-1.0.1.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
