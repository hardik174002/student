FROM openjdk:17-slim
WORKDIR /app
COPY app.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","app.jar"]