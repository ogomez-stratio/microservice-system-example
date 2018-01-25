FROM qa.stratio.com/stratio/java-microservice-dockerbase:0.2.0

VOLUME /tmp

# Create User SCD for
RUN adduser -D scduser

ARG JAR_VERSION
ARG JAR_ARTIFACT
ADD ${JAR_ARTIFACT}-${JAR_VERSION}.jar app.jar

RUN sh -c 'touch /app.jar'

# All the remaining actions will be performed as user "java"
ENV JAVA_OPTS="-Xmx512m -Xms256m" && \
    adduser -D -u 1000 user && \
    chown -R user /data

ENV JAVA_OPTS="-Xmx512m -Xms256m"

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]