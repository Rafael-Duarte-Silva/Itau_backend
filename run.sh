#!/bin/bash

JAR_FILE_NAME="./target/itau-0.0.1-SNAPSHOT.jar"
JAVA_EXEC_PATH="/usr/bin/java"

# DATA DOG
export DD_ENV="dev"
export DD_SERVICE="api-itau"
export DD_VERSION="1.0"

JAVA_OPTIONS="${JAVA_OPTIONS} -javaagent:./dd-java-agent.jar -XX:FlightRecorderOptions=stackdepth=256"

"$JAVA_EXEC_PATH" $JAVA_OPTIONS -jar "$JAR_FILE_NAME"