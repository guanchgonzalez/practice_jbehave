# Maven and JBehave practice test for TecAlliance into Docker

FROM maven:alpine as builder

RUN apk add git

COPY pom.xml /tmp/
COPY run-container.sh /
WORKDIR /app/practice_jbehave
RUN /run-container.sh

FROM maven:alpine
COPY --from=builder /app/practice_jbehave/practice_jbehave/target/practice_jbehave-0.1-SNAPSHOT.jar /
CMD java -jar /practice_jbehave-0.1-SNAPSHOT.jar
