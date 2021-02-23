# Dockerfile References: https://docs.docker.com/engine/reference/builder/

FROM openjdk:11

COPY target/gamble-0.0.1-SNAPSHOT.jar ./

# From https://github.com/joewalnes/websocketd/releases/tag/v0.3.1
COPY websocketd-0.3.1_amd64.deb ./

RUN apt-get install /websocketd-0.3.1_amd64.deb 

RUN mkdir /docs

# Sample page for load balancer health checks
COPY index.html docs/check.html

# Application served from here (?)
COPY index.html docs/index.html

# Remove HIGH vulnerable curl
RUN apt-get -y remove --purge curl

EXPOSE 80

# Command to run the executable
CMD websocketd --port=80 --staticdir docs java -cp gamble-0.0.1-SNAPSHOT.jar gamble.Main
