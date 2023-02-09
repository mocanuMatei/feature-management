FROM openjdk:17
COPY /build/libs/features-management-0.0.1-SNAPSHOT.jar /app/features-management.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/features-management.jar"]