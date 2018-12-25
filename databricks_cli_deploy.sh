#!/bin/bash

set -e 

artifacts_alias=$1
drop_root="$artifacts_alias"/drop

# install jq and databricks cli
sudo apt-get install -y jq
pip install databricks-cli

# copy artifacts to dbfs
databricks fs cp --overwrite "$drop_root"/target/sparkcicd-0.0.1-SNAPSHOT.jar dbfs:/
databricks fs cp --overwrite "$drop_root"/test.txt dbfs:/

# create a job and run it immediately
job_id=$(databricks jobs create --json-file "$drop_root"/databricks_job.json | jq '.job_id')
run_id=$(databricks jobs run-now --job-id "$job_id" | jq '.run_id')

# wait max 10 minutes for the run to complete, report status
for i in {1..10}
do
  state=$(databricks runs get --run-id "$run_id" | jq '.state')
  life_cycle_state=$(jq -r '.life_cycle_state' <<< $state)
  result_state=$(jq -r '.result_state' <<< $state)
  if [ "$life_cycle_state" = "TERMINATED" ]; then
    if [ "$result_state" = "SUCCESS" ]; then
      echo "Job $job_id run $run_id succeeded"
      exit 0
    else
      echo "Job $job_id run $run_id didn't succeed: $result_state"
      exit 1
    fi
  fi
  sleep 1m
done
echo "Job $job_id run $run_id didn't terminate after 10 minutes: $life_cycle_state"
exit 1
