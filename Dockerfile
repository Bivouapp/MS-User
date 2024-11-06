# --- Build stage for Spring Boot application ---
FROM gradle:8-jdk21-alpine AS build

# Set the working directory
WORKDIR /app

# Copy the Kotlin Gradle build file
COPY build.gradle.kts settings.gradle.kts ./

# Fetch dependencies
RUN gradle dependencies --no-daemon

# Copy the application source code
COPY . .

# Build the application
RUN gradle build --no-daemon

# --- Runtime stage ---
FROM openjdk:21-jdk-slim


# Install PostgreSQL (adding PostgreSQL to an OpenJDK image)
RUN apt-get update && \
    apt-get install -y postgresql postgresql-contrib && \
    rm -rf /var/lib/apt/lists/*

# Set the working directory for the application
WORKDIR /app

# Copy the built application jar from the build stage
COPY --from=build /app/build/libs/*.jar app.jar


# Copy your init.sql script
COPY init.sql /docker-entrypoint-initdb.d/


# Expose the application port and PostgreSQL port
EXPOSE 8080 5432

# Start PostgreSQL and the application
CMD service postgresql start && java -jar app.jar