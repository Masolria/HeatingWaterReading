FROM openjdk:17
#RUN
COPY src ./HeatingWaterReading/src
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
WORKDIR /app
ENTRYPOINT ["java","ru.masolria.hwr",,"Console.java"]