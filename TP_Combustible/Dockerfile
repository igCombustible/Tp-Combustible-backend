FROM eclipse-temurin:17-jdk-alpine AS builder

WORKDIR /app

COPY . .

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=builder /app/target/TP_Combustible-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/app.jar"]