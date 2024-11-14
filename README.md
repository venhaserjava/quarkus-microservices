# quarkus-microservices
### na pasta do microservice
cd customer
### Adicionar no Quarkus a extensão do Docker-compose ao pom.xml
./mvnw quarkus:add-extension -Dextensions="container-image-docker"

### Build da image docker
./mvnw clean package -Dquarkus.container-image.build=true -DskipTests

### para conferir a imagem gerada
docker images

