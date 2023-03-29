FROM maven:3.9.0-eclipse-temurin-17-alpine as maven_build

RUN mvn -version && java -version

ENV HOME=/usr/app

RUN mkdir -p $HOME

WORKDIR $HOME

ADD pom.xml $HOME

RUN mvn verify clean --fail-never

ADD . $HOME

RUN mvn package -DskipTests

FROM eclipse-temurin:17-alpine

MAINTAINER blackparadise0407@gmail.com

ENV HOME=/usr/app

EXPOSE 8082

COPY --from=maven_build $HOME/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]