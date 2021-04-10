## Criando a imagem nativa

- ./mvnw package -Dpackaging=native-image -Pgraalvm

## Criando uma imagem (docker) c/ GraalVM:
- ./mvnw package -Dpackaging=docker-native
