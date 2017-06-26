FROM java:8

MAINTAINER ClearSky

EXPOSE 8888

ADD clearsky-container.jar /opt/clearsky-container.jar
RUN chmod +x /opt/clearsky-container.jar

ADD start_up.sh /opt/start_up.sh
RUN chmod +x /opt/start_up.sh

CMD bash /opt/start_up.sh