#!/bin/bash

echo "spark.master  spark://localhost:7077" > /opt/spark/conf/spark-defaults.conf

exec /opt/spark/bin/spark-class org.apache.spark.deploy.master.Master --ip localhost --port 7077 --webui-port 8080

