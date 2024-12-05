FROM maven AS build
WORKDIR /app
COPY . .
RUN mvn clean install

FROM eclipse-temurin:17.0.13_11-jdk-noble AS deploy

ARG JAR_FILE="/app/target/dilemme-prisonnier.jar"
ARG DATABASE_URL

ENV spring_profiles_active="prod"

COPY --from=build ${JAR_FILE} /opt/app/app.jar

RUN chmod u+x /opt/app/app.jar

WORKDIR /opt/app

RUN echo DATABASE_URL=${DATABASE_URL} > .env

EXPOSE 8080

CMD ["java", "-jar", "/opt/app/app.jar"]