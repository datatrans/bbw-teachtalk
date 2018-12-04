FROM maven:3.6.0-jdk-11

WORKDIR /app

ADD src ./src
ADD pom.xml ./

RUN mvn package

FROM openjdk:11

COPY --from=0 /app/target/bbw-stickershop.jar bbw-stickershop.jar
ADD docker/run.sh ./

ENV datatransUsername ''
ENV datatransPassword ''

EXPOSE 8080

CMD [ "./run.sh" ]
