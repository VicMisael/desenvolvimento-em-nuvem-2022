FROM maven:3.8-jdk-11 AS build
WORKDIR /usr/src/app/
COPY pom.xml ./pom.xml
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn -B -DskipTests=true package
ENTRYPOINT ["java","-jar","/usr/src/app/target/patrimoniomanager-0.0.1-SNAPSHOT.jar"]
