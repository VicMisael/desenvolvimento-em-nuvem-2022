# FROM maven:3.8-jdk-11 AS build
# COPY src /usr/src/app/src
# COPY pom.xml /usr/src/app
# RUN mvn -f /usr/src/app/pom.xml -B -DskipTests=true package

FROM adoptopenjdk/openjdk11
# COPY --from=build /usr/src/app/target/subscription-0.0.1.jar /usr/app/subscription-0.0.1.jar
# COPY ./target/subscription-0.0.1.jar /usr/app/subscription-0.0.1.jar
COPY target/subscription-0.0.1.jar /usr/app/subscription-0.0.1.jar
ENTRYPOINT ["java","-jar","/usr/app/subscription-0.0.1.jar"]
