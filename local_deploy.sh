#!/bin/bash

echo "Deploying local"

# Build war
ant build
# Remove existing deployment
rm ~/Development/wildfly-preview-26.1.3.Final/standalone/deployments/LAB3.war
# Add new deployment
cp ./build/LAB3.war ~/Development/wildfly-preview-26.1.3.Final/standalone/deployments/LAB3.war
