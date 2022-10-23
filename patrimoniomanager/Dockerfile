FROM maven:3.8-jdk-11 AS build
COPY pom.xml /usr/src/app/pom.xml
RUN mvn -f /usr/src/app/pom.xml -B dependency:resolve
COPY src /usr/src/app/src
RUN mvn -f /usr/src/app/pom.xml  -DskipTests=true package
ENTRYPOINT ["java","-jar","/usr/src/app/target/patrimoniomanager-0.0.1-SNAPSHOT.jar"]
