def myJob = freeStyleJob('GradlePropertiesCheck')
myJob.with {
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
