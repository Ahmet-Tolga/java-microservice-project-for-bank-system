# Base image olarak OpenJDK kullanıyoruz
FROM openjdk:17-jdk-slim

WORKDIR /app

# Uygulama JAR dosyasını çalışma dizinine kopyala
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Portları belirle (opsiyonel, sadece dokümantasyon için)
EXPOSE 8088

# Spring Boot uygulamasını çalıştır
ENTRYPOINT ["java", "-jar", "app.jar"]
