FROM eclipse-temurin:17-jre-alpine
EXPOSE 8000
COPY /target/NumberGeneratorService.jar /NumberGeneratorService.jar
ENTRYPOINT ["java","-jar","/NumberGeneratorService.jar"]