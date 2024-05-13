FROM gradle:7.5-jdk17

WORKDIR /app

COPY build.gradle settings.gradle checkstyle_rules.xml ./
COPY src ./src/

RUN gradle build

ENTRYPOINT ["gradle", "run"]
