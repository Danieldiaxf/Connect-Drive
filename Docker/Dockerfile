FROM eclipse-temurin:26-jdk AS build


WORKDIR /app


COPY ../connect/mvnw .
COPY ../connect/.mvn .mvn
COPY ../connect/pom.xml .


RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline -B


COPY ../connect/src src


RUN ./mvnw clean package -DskipTests


FROM eclipse-temurin:26-jre


WORKDIR /app


COPY --from=build /app/target/*.jar app.jar


EXPOSE 8080


ENTRYPOINT ["java", "-jar", "app.jar"]
