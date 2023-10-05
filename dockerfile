FROM eclipse-temurin:17-jdk-jammy as base
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:resolve
COPY src ./src
#ENTRYPOINT [ "java", "-Dspring.profiles.active=dev", "-jar", "/projekt-pekao.jar"]
FROM base as development
CMD ["./mvnw", "spring-boot:run", "-Dspring-boot.run.profiles=mysql", "-Dspring-boot.run.jvmArguments='-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000'"]


FROM base as build
RUN ./mvnw package

FROM eclipse-temurin:17-jre-jammy as production
EXPOSE 8081
COPY --from=build /app/target/projekt-pekao-*.jar /projekt-pekao.jar

#,"-Dspring.profiles.active=dev"
