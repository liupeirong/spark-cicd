[![Build Status](https://travis-ci.com/liupeirong/spark-cicd.svg?branch=master)](https://travis-ci.com/liupeirong/spark-cicd)
[![Build Status](https://dev.azure.com/paigedevops/spark-github-cd/_apis/build/status/github.spark-cicd.yaml?branchName=master)](https://dev.azure.com/paigedevops/spark-github-cd/_build/latest?definitionId=6?branchName=master)

# Spark CI/CD

This sample creates a simple word count Spark application. CI/CD is set up as following - 
* When code is checked into a feature branch, Travis CI will kick off a validation build and run unit tests. 
* Once the validation build succeeds, the PR can be merged to the master branch. 
* Once the PR is merged to the master branch, Azure DevOps will kick off a [build](https://dev.azure.com/paigedevops/spark-github-cd/_build?definitionId=6). When azure-pipelines.yml exists in the root folder of the project, Azure DevOps will attempt to use it as the build pipeline for the project. It also has continuous integration enabled by default. Alternatively, you can create a build pipeline using Azure DevOps visual designer, as seen in this [equivalent pipeline](https://dev.azure.com/paigedevops/spark-github-cd/_build?definitionId=4). 
* After the build succeeds, Azure DevOps will run a [release pipeline](https://dev.azure.com/paigedevops/spark-github-cd/_release?view=mine&definitionId=1) that invokes a [bash script](databricks_cli_deploy.sh) that uses ```Databricks Cli``` to create a [job](databricks_job.json) in Azure Databricks, run spark-submit of the built jar, and report back if the job ran successfully. 

It's also possible to run unit tests with Spark applications using Azure DevOps alone by
* installing dependencies on the agent, 
* or using a [private build agent](https://docs.microsoft.com/en-us/azure/devops/pipelines/agents/v2-linux?view=vsts) with dependencies already installed, 
* or [running a build job on a container](https://docs.microsoft.com/en-us/azure/devops/pipelines/process/container-phases?view=vsts&tabs=yaml). See [this example](https://dev.azure.com/paigedevops/azure-samples/_build?definitionId=5).

We use Travis CI because it's simple to use, and we demonstrate you can integrate GitHub with Azure DevOps for a complete CI/CD process. 