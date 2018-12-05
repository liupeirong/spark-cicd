#!/bin/bash

exec env SPARK_WORKER_CORES=1 SPARK_WORKER_MEMORY=1g /opt/spark/bin/spark-class org.apache.spark.deploy.worker.Worker --webui-port 8082 spark://localhost:7077

