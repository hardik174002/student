FROM openjdk:17
WORKDIR /app
COPY app.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","app.jar"]