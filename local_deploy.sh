#!/bin/bash

# Function to display usage
usage() {
    echo "Usage: $0 [-b] [-d] [-r] [-h]"
    echo "  -b    Build the WAR file"
    echo "  -d    Deploy the WAR file"
    echo "  -r    Restart the WildFly server"
    echo "  -h    Display this help message"
}

# Default options
BUILD_WAR=false
RESTART_SERVER=false
DEPLOY_WAR=false

# Parse options
while getopts "bdrh" opt; do
    case "$opt" in
        b) BUILD_WAR=true ;;
        d) DEPLOY_WAR=true ;;
        r) RESTART_SERVER=true ;;
        h) usage ; exit 0 ;;
    esac
done

if [ "$BUILD_WAR" = false ] && [ "$RESTART_SERVER" = false ] && [ "$DEPLOY_WAR" = false ]; then
    echo "Error: At least one option must be specified."
    usage
    exit 1
fi

WAR_NAME="LAB3.war"
WILDFLY_LOCATION="$HOME/Development/wildfly-preview-26.1.0.Final"

# Build WAR
if [ "$BUILD_WAR" = true ]; then
    echo "Building WAR..."
    ant build
fi

# Deploy WAR
if [ "$DEPLOY_WAR" = true ]; then
    echo "Deploying WAR..."
    # Remove existing deployment
    rm -f "$WILDFLY_LOCATION"/standalone/deployments/"$WAR_NAME"*
    # Add new deployment
    cp ./build/"$WAR_NAME" "$WILDFLY_LOCATION"/standalone/deployments/"$WAR_NAME"
fi

# Restart WildFly server
if [ "$RESTART_SERVER" = true ]; then
    echo "Restarting WildFly server..."
    "$WILDFLY_LOCATION"/bin/standalone.sh \
    -b 0.0.0.0 \
    -Dcom.sun.management.jmxremote \
    -Dcom.sun.management.jmxremote.authenticate=false \
    -Dcom.sun.management.jmxremote.ssl=false \
    -Dcom.sun.management.jmxremote.port=7203 \
    -Dcom.sun.management.jmxremote.rmi.port=7203 \
    -Djava.rmi.server.hostname=0.0.0.0 \
    -Djboss.bind.address.management=0.0.0.0 \
    -Djboss.bind.address=0.0.0.0
fi