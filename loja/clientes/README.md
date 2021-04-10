## Criando a imagem nativa

- ./mvnw package -Dpackaging=native-image 

## Criando uma imagem (docker) c/ GraalVM:
- ./mvnw package -Dpackaging=docker-native -Pgraalvm



##bkp
FROM openjdk:11
ARG JAR_FILE=target/clientes-0.1.jar
CMD ./wait-for -t 60 $CONSUL_HOST:$CONSUL_PORT -- \
./wait-for -t 60 $MARIADB_HOST:$MARIADB_PORT -- \
echo "All dependencies ready. Starting application..." && \
java ${JAVA_OPTS} -jar app.jar
COPY ${JAR_FILE} app.jar
COPY wait-for wait-for

FROM openjdk:11
CMD ./wait-for -t 60 $CONSUL_HOST:$CONSUL_PORT -- \
    ./wait-for -t 60 $DB_HOST:$DB_PORT -- \
        echo "All dependencies ready. Starting application..." && \
        java ${JAVA_OPTS} -jar app.jar
COPY target/clientes-0.1.jar app.jar
COPY wait-for wait-for
