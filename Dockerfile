# Unclear if this image is the best choice
FROM openjdk:17-slim

RUN apt-get update && apt-get install -y \
    maven \
    gettext-base \
    && rm -rf /var/lib/apt/lists/*
# Not sure if the two latter are needed

WORKDIR /app

COPY pom.xml build_and_copy.sh .
COPY src ./src

# Make the script executable
RUN chmod +x build_and_copy.sh

# Set the entrypoint to the shell script
ENTRYPOINT ["./build_and_copy.sh"]
