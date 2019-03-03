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

  stage('Cleanup') {
    step([$class: 'WsCleanup'])
  }
}