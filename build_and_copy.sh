#!/bin/bash

mvn clean package -DskipTests

if [ $? -eq 0 ]; then
  if [ ! -d "/output/claudeapi" ]; then
    mkdir -p "/output/claudeapi"
  fi
    
  cp target/*.jar /output/claudeapi/
  cp pom.xml /output/claudeapi/
  echo "Build and copy successful."
else
  echo "Build failed."
  exit 1 # Exit with an error code
fi
