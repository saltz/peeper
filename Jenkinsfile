node('maven') {
  def branch
  stage('Clone') {
    checkout scm
    branch = env.BRANCH_NAME
  }

  stage('Maven build') {
    sh "mvn -B -V -U -e clean package"
  }

  stage('Test and archive') {
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