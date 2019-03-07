node('maven') {
  def branch
  stage('Clone') {
    checkout scm
    branch = env.BRANCH_NAME
  }

  stage('Tag build') {
    def pom = readMavenPom file: 'pom.xml'
    currentBuild.displayName = 'peeper-' + pom.version
  }

  stage('Maven build and package') {
    sh "mvn -B -V -U -e clean package"
  }

  stage('Record test results') {
    junit testResults: '**/target/**/TEST*.xml'
  }

  stage('Deploy to environment') {
    parallel devolopment: {
        node('docker') {
          def app
          if (branch == 'develop') {
            stage('Clone') {
              checkout scm
            }

            stage('Build docker image') {
              app = docker.build('saltz/peeper')
            }

            stage('Push docker image') {
              docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials') {
                app.push('dev')
              }
            }

            stage('Cleanup') {
              sh 'docker image prune -f -a'
            }
          }
        }
      },
      production: {
        node('docker') {
          def app
          if (branch == 'master') {
            stage('Clone') {
              checkout scm
            }

            stage('Build docker image') {
              app = docker.build('saltz/peeper')
            }

            stage('Push docker image') {
              docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials') {
                app.push('release')
              }
            }

            stage('Cleanup') {
              sh 'docker image prune -f -a'
            }
          }
        }
      }
  }

  stage('Cleanup') {
    step([$class: 'WsCleanup'])
  }

}