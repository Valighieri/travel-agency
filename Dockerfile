FROM openjdk:17
ADD /target/travel-agency.jar travel-agency.jar
ENTRYPOINT ["java", "-jar", "travel-agency.jar"]