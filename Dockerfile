#FROM postgres:alpine
#ENV POSTGRES_PASSWORD homer_forever
#ENV POSTGRES_DB security
#EXPOSE 5432
FROM maven:3.8.6-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml -DskipTests clean package

FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/security_demo-0.0.1-SNAPSHOT.jar /usr/local/lib/demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/demo.jar"]