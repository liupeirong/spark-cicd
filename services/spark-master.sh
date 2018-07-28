#!/bin/bash

echo "spark.master  spark://$HOSTNAME:7077" > /opt/spark-2.3.1-bin-hadoop2.6/conf/spark-defaults.conf

exec /opt/spark-2.3.1-bin-hadoop2.6/bin/spark-class org.apache.spark.deploy.master.Master --ip $HOSTNAME --port 7077 --webui-port 8080

