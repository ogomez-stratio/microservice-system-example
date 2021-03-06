#!/bin/bash
# wait for zookeeper
/opt/scripts/wait-for-it.sh zookeeper:2181 -s -t 60

#run kafka
/opt/sds/kafka/bin/kafka-server-start.sh /etc/sds/kafka/server.properties &
echo "KAFKA UP !!!!!!!!!!!"

service ssh restart

# an endless process to avoid container to stop
tail -f /dev/null
