[![Build Status](https://travis-ci.com/liupeirong/spark-cicd.svg?branch=master)](https://travis-ci.com/liupeirong/spark-cicd)

# Spark CI/CD

This sample creates a simple word count Spark application. CI/CD is set up as following - 
* When code is checked into a feature branch, Travis CI will kick off a validation build and run unit tests. 
* Once the validation build succeeds, the PR can be merged to the master branch. 
* Once the PR is merged to the master branch, Azure DevOps will kick off a build. 
* After the build succeeds, Azure DevOps will use ```databricks cli``` to deploy a job to Azure Databricks which runs spark-submit of the built jar, and report back if the job ran successfully. 

It's also possible to run unit tests with Spark applications using Azure DevOps alone by
* installing dependencies on the agent, 
* or using a [private build agent](https://docs.microsoft.com/en-us/azure/devops/pipelines/agents/v2-linux?view=vsts) with dependencies already installed, 
* or [running a build job on a container](https://docs.microsoft.com/en-us/azure/devops/pipelines/process/container-phases?view=vsts&tabs=yaml). 

We use Travis CI because it's simple to use, and we demonstrate you can integrate GitHub with Azure DevOps for a complete CI/CD process. 