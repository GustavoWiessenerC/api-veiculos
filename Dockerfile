# Build aplicação
FROM ubuntu:latest AS build

# Instalação do OpenJDK
RUN apt-get update
RUN apt-get install openjdk-17-jdk -y

# Defina o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie os dados da app supondo o Dockerfile que esteja no diretório raiz
COPY . .

# Conceda permissão de execução ao gradlew
RUN chmod +x ./gradlew

# Run build para geração do Jar
RUN ./gradlew bootJar --no-daemon

# Final Stage - Slim JDK
FROM openjdk:17-jdk-slim

# Exponha a porta em que seu aplicativo Spring Boot escuta (altere-a se necessário)
EXPOSE 8080

# Defina o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie o JAR do estágio de construção para o estágio final
COPY --from=build /app/build/libs/api-veiculos-0.0.1-SNAPSHOT.jar app.jar

# Comando para executar seu aplicativo Spring Boot quando o contêiner for iniciado
ENTRYPOINT ["java", "-jar", "app.jar"]
