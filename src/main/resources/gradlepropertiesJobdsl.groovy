def publishJob = freeStyleJob('GradlePropertiesPublish')
publishJob.with {
    description 'This is job for Gradle upload artifactory properties project'
    scm{
        git {
            remote {
                name('origin')
                url('TBD')
            }
            branch('master')
        }
    }
    steps{
        gradle{
            tasks('clean')
            tasks('artifactorypublish')
            useWrapper(true)
            fromRootBuildScriptDir(true)
        }
    }
}

def reviewJob = freeStyleJob('GradlePropertiesReview')
reviewJob.with {
    description 'This is job for review new changes in Gradle artifactory properties project'
    scm{
        git {
            remote {
                name('origin')
                url('TBD')
            }
            branch('*/ready/**')
        }
    }
    steps{
        gradle{
            tasks('clean')
            tasks('test')
            useWrapper(true)
            fromRootBuildScriptDir(true)
        }
    }
    configure{ project ->
        project / publishers << 'org.jenkinsci.plugins.pretestedintegration.PretestedIntegrationPostCheckout' {}
        project / buildWrappers << 'org.jenkinsci.plugins.pretestedintegration.PretestedIntegrationBuildWrapper' {
            scmBridge('class': 'org.jenkinsci.plugins.pretestedintegration.scm.git.GitBridge') {
                branch 'master'
                integrationStrategy('class': 'org.jenkinsci.plugins.pretestedintegration.scm.git.SquashCommitStrategy')
                repoName 'origin'
            }
            rollbackEnabled false
        }
    }
}
