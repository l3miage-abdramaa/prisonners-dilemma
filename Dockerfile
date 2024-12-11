FROM maven AS build
WORKDIR /app
COPY . .
RUN mvn install:install-file -Dfile=lib/'strategie_g02_7-1.jar' -DgroupId='fr.uga.miage.m1' -DartifactId=strategie_g02_7 -Dpackaging=jar
RUN mvn install

FROM eclipse-temurin:21.0.5_11-jdk-noble AS deploy

ARG JAR_FILE="/app/target/dilemme-prisonnier.jar"
ARG DATABASE_URL

ENV spring_profiles_active="prod"

COPY --from=build ${JAR_FILE} /opt/app/app.jar
COPY --from=build /app/lib /opt/app/

RUN chmod u+x /opt/app/app.jar

WORKDIR /opt/app

RUN echo DATABASE_URL=${DATABASE_URL} > .env

EXPOSE 8080

CMD ["java", "-jar", "/opt/app/app.jar"]