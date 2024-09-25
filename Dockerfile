# Use a imagem base com OpenJDK 17 e Alpine Linux
FROM openjdk:17-alpine as build

# Set o diretório de trabalho para a compilação
WORKDIR /app

# Instale Maven
RUN apk add --no-cache maven

# Copie o arquivo pom.xml e baixe as dependências (go-offline)
COPY pom.xml /app
RUN mvn dependency:go-offline

# Copie o restante do código para o container
COPY . /app

# Compile o projeto e empacote em um JAR
RUN mvn clean package -DskipTests

# Use uma imagem leve para o runtime final
FROM openjdk:17-alpine

# Set o diretório de trabalho para o runtime
WORKDIR /app

# Copie o JAR gerado no estágio de build
COPY --from=build /app/target/sales-project.jar /app/sales-project.jar
COPY src/main/resources/application.yaml /app/config/application.yaml
# Instale pacotes necessários
RUN apk add -U tzdata ttf-dejavu && \
    apk add --update msttcorefonts-installer fontconfig && \
    fc-cache -f && \
    rm -rf /var/cache/apk/*

# Defina o fuso horário
RUN cp /usr/share/zoneinfo/America/Sao_Paulo /etc/localtime && \
    echo "America/Sao_Paulo" > /etc/timezone && \
    apk del -U tzdata

# Defina o ponto de entrada para o container
CMD ["java", "-Dspring.profiles.active=prod", "-Duser.timezone=GMT-03:00", "-Dspring.config.location=classpath:application.yaml,file:/app/config/application.yaml", "-jar", "sales-project.jar"]
