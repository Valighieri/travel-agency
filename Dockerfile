FROM maven:3.8.3-openjdk-17 AS build
COPY ./src /travel-agency/src
COPY ./pom.xml /travel-agency

WORKDIR /travel-agency
RUN mvn -f pom.xml clean install -DskipTests

FROM openjdk:17-slim
COPY --from=build /travel-agency/target/*.jar travel-agency.jar
ENTRYPOINT ["java","-jar","travel-agency.jar"]