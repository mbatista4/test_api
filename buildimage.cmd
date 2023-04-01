call mvn clean
call mvn package
call docker kill --signal=1 bank_api
call docker rm -v bank_api
call docker build -f ./DockerFile -t bank-rest .
call docker run --name bank_api --env-file "./env.properties" -p 127.0.0.1:8080:8080/tcp -d bank-rest:latest
