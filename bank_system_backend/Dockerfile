FROM gradle:7.5-jdk17

WORKDIR /app

COPY build.gradle settings.gradle ./
COPY src ./src/

RUN gradle build

ENTRYPOINT ["gradle", "run"]
