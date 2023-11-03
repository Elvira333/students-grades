FROM openjdk:17-jdk-slim
EXPOSE 8080
ADD /out/artifacts/students_grades_jar/students-grades.jar students-grades.jar
ENTRYPOINT ["java", "-jar", "students-grades.jar"]
