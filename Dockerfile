# --- Fase de construcción (Build) ---
# Usamos Maven con JDK 17 compatible con ARM64 (Apple Silicon)
FROM maven:3.9.6-eclipse-temurin-17 AS build
# Forzamos x86_64 para evitar problemas con dependencias nativas
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# --- Fase de ejecución (Runtime) ---
# Usamos JRE oficial compatible con ARM64 (alternativas: eclipse-temurin:17-jre o amazoncorretto:17)
FROM eclipse-temurin:17-jre
# Versión ARM-compatible
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]