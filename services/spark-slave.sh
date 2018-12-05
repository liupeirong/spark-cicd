#!/bin/bash

exec env SPARK_WORKER_CORES=1 SPARK_WORKER_MEMORY=1g HOSTNAME=$HOSTNAME HOSTNAME2=$HOSTNAME /opt/spark/bin/spark-class org.apache.spark.deploy.worker.Worker --webui-port 8081 spark://$HOSTNAME:7077

