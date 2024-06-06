#!/bin/bash

echo "Starting deployment..."

WAR_NAME="LAB3.war"
WILDFLY_LOCATION="$HOME/Development/wildfly-preview-26.1.0.Final"

# Build war
ant build
# Remove existing deployment
rm -f "$WILDFLY_LOCATION"/standalone/deployments/"$WAR_NAME"*
# Add new deployment
cp ./build/"$WAR_NAME" "$WILDFLY_LOCATION"/standalone/deployments/"$WAR_NAME"
# Run wildfly
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
