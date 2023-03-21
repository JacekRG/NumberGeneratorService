FROM eclipse-temurin:17-jre-alpine
COPY /target/NumberGeneratorService.jar /NumberGeneratorService.jar
ENTRYPOINT ["java","-jar","/NumberGeneratorService.jar"]