 mvn clean && mvn package

docker build -f ./DockerFile -t bank-rest .

docker run --env-file "./env.properties" -p 127.0.0.1:8080:8080/tcp  bank-rest:latest