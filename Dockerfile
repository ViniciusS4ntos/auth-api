# -------- STAGE 1: BUILD --------
FROM eclipse-temurin:17-jdk AS build

WORKDIR /app
COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew build -x test

# -------- STAGE 2: RUNTIME --------
FROM eclipse-temurin:17-jre

WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]