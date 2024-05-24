#!/bin/bash

echo "Deploying to Helios"

# Build war
ant build
# Remove existing deployment
ssh -p 2222 s368328@se.ifmo.ru "rm wildfly/wildfly-preview-26.1.3.Final/standalone/deployments/LAB3.war"
# Add new deployment
scp -P 2222 ./build/LAB3.war s368328@se.ifmo.ru:wildfly/wildfly-preview-26.1.3.Final/standalone/deployments
