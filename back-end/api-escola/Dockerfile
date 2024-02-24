# Usa a imagem base com a versão da linguagem usada no projeto
FROM openjdk:17-jdk-alpine

# Define o diretorio base padrão
WORKDIR /app

# Copia o arquivo jar para a pasta base
COPY target/api-escola.jar /app/main.jar

# Expoẽ a porta usada pela aplicação
EXPOSE 8080

# Comando que inicia a aplicação
CMD ["java", "-jar", "main.jar"]
