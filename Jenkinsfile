#!groovy
@Library('main_shared') _

String organization = 'saltrepositories'
String repo = 'din-api'

Map settings = [
    sonar_key: 'peeper-api'
]


mavenBuildAndDeploy(organization, repo, settings)