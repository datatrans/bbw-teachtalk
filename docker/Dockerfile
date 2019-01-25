FROM maven:3.6.0-jdk-11 as build-env

WORKDIR /app

COPY src ./src
COPY pom.xml ./

RUN mvn package

FROM openjdk:11

COPY --from=build-env /app/target/bbw-stickershop.jar bbw-stickershop.jar
COPY docker/run.sh ./

ENV datatransUsername ''
ENV datatransPassword ''

EXPOSE 8080

CMD [ "./run.sh" ]
