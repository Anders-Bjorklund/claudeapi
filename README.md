# claudeapi

## Building with Docker
To build the code fully with Docker, perform the following steps:

1. Create some output destination for the compiled files. I recommend a Docker-managed volume.
2. Build the Docker image using `docker build -t <my-image-name> .`.
3. Run the build process using `docker run --rm -v <build-destination>:/output <my-image-name>`.

The built files should now be found at `<build-destination>/claudeapi`.
