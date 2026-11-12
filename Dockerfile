# ---------- Estágio de build ----------
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

COPY connect/mvnw .
COPY connect/.mvn .mvn
COPY connect/pom.xml .
RUN chmod +x mvnw && ./mvnw dependency:go-offline -B

COPY connect/src src
RUN ./mvnw clean package -DskipTests -B

# ---------- Estágio de execução ----------
FROM eclipse-temurin:17-jre
WORKDIR /app

RUN groupadd --system app && useradd --system --gid app app
COPY --from=build --chown=app:app /app/target/connect-0.0.1-SNAPSHOT.war app.war
USER app

EXPOSE 8080
ENTRYPOINT ["java", "-XX:MaxRAMPercentage=75", "-jar", "app.war"]