# Base image olarak OpenJDK kullanıyoruz
FROM openjdk:17-jdk-slim

# Çalışma dizinini oluştur ve ayarla
WORKDIR /app

# Uygulama JAR dosyasını çalışma dizinine kopyala
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Portları belirle (opsiyonel, sadece dokümantasyon için)
EXPOSE 5000

# Spring Boot uygulamasını çalıştır
ENTRYPOINT ["java", "-jar", "app.jar"]
