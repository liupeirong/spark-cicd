#!/bin/bash

exec env SPARK_WORKER_CORES=1 SPARK_WORKER_MEMORY=1g /opt/spark-2.3.1-bin-hadoop2.6/bin/spark-class org.apache.spark.deploy.worker.Worker --webui-port 8081 spark://$HOSTNAME:7077

