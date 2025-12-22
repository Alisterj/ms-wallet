FROM debian:bookworm-slim

ARG APP_NAME
ENV APP_NAME=${APP_NAME}
ENV JAVA_HOME=/opt/java/openjdk
COPY --from=eclipse-temurin:17-jdk $JAVA_HOME $JAVA_HOME
ENV PATH="${JAVA_HOME}/bin:${PATH}"

# Установка часового пояса операционной системы
RUN ln -sf /usr/share/zoneinfo/Europe/Moscow /etc/localtime

WORKDIR /app/
COPY ./target/${APP_NAME}*.jar ${APP_NAME}.jar

EXPOSE 8080
EXPOSE 8079

# Запуск в шелле чтобы можно было использовать переменные среды в блоке CMD
CMD [ "sh", \
      "-c", \
      "java \
      -Duser.timezone=Europe/Moscow, \
      -jar \
      ${APP_NAME}.jar" \
    ]