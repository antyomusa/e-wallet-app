FROM eclipse-temurin:17-jdk
LABEL authors="steph"
EXPOSE 8899
ADD target/ewallet-0.0.1-SNAPSHOT.jar ewallet.jar
ENTRYPOINT ["java", "-jar", "/ewallet.jar"]

