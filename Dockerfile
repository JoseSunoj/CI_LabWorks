# this Dockerfile contains steps for multi-stage builds

# stage-1 
# uses the src folder and pom.xml file presents in the repository
# to build an image of the package

FROM maven:3-jdk-11-slim as build
COPY ./src ./src
COPY ./pom.xml ./pom.xml
# RUN mvn clean package
mvn clean install -U

# stage-2 
# copies the above build image to the working dir
# and run the command in CMD

FROM openjdk:11-jre-slim

# ARG artifactid
# ARG version
# ENV artifact=${artifactid}-${version}.jar
# ARG artifact
# ENV artifact = ${artifact}

WORKDIR /lyit

# COPY --from=build target/${artifact} ./app.jar
# CMD ["java", "-jar", "./app.jar"]

COPY --from=build target/IbanValidator-*.jar ./apps/IbanValidator-*.jar
CMD ["java", "-jar", "./apps/IbanValidator-*.jar"]
