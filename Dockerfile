FROM gradle:7.5.0-jdk17
WORKDIR /opt/app
COPY ./build/libs/Eternal-Strife-1.0-SNAPSHOT.jar ./

ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar Eternal-Strife-1.0-SNAPSHOT.jar"]