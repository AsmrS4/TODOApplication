FROM maven:3.8-openjdk-17-slim AS builder
WORKDIR /build
COPY pom.xml .
COPY src ./src
RUN mvn clean package

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/your-app.jar app.jar\
EXPOSE 8092
ENTRYPOINT ["java", "-jar", "app.jar"]