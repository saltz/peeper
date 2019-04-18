#!groovy
@Library('main_shared') _

Map projectSettings = [
    repo: "saltz",
    name: "peeper"
]

Map sonarSettings = [
    key: "blockr-data-access"
]

mavenBuildAndDeploy(projectSettings, sonarSettings)