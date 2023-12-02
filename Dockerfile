# Stage 1: Build the application
FROM eclipse-temurin:21 AS build
COPY . /home/app
WORKDIR /home/app
RUN ./gradlew bootJar --no-daemon

# Stage 2: Create the final Docker image
FROM eclipse-temurin:21
COPY --from=build /home/app/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080
