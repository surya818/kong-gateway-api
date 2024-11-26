
FROM openjdk:latest
# Set up working directory and copy HiveMQ Edge from the builder stage
WORKDIR /opt/kong
RUN chmod +x -R ./

COPY . .
ENTRYPOINT ["/bin/bash", "lib/runtests.sh"]
