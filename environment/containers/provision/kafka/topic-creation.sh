#!/bin/bash

####################################
#          DANGER ZONE             #
# DONT MODIFY ANYTHING BEYOND THIS #
# LINE IF YOU ARE NOT SURE         #
####################################
ssh deployment "/opt/sds/deployment/scripts/kafka/initialize_topics.sh -f"
