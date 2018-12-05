#!/bin/bash

echo "spark.master  spark://$HOSTNAME:7077" > /opt/spark/conf/spark-defaults.conf

exec /opt/spark/bin/spark-class org.apache.spark.deploy.master.Master --ip $HOSTNAME --port 7077 --webui-port 8080

