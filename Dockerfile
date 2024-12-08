
FROM openjdk:latest
# Set up working directory and copy HiveMQ Edge from the builder stage
WORKDIR /opt/kong
RUN microdnf install -y findutils && microdnf clean all
COPY . .
RUN chmod +x -R ./
ENTRYPOINT ["/bin/bash", "lib/runtests.sh"]
