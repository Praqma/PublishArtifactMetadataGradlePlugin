# PublishArtifactMetadataGradlePlugin
This plugin allows publish an artifact properties to Artifactory

implementation-class=com.praqma.ArtifactMetadataPlugin

Usage:

<pre><code> task setProperties(type: SetPropertyTask) {
    url = "${artifactory_contextUrl}"
    repoKey = "${artifactory_repoKey}"
    itemPath = "/${component_name}/${name}/${version}"

    propertiesMap = [
            'build.name': System.env.JOB_NAME ?: System.env.HOSTNAME,
            'build.number': System.env.BUILD_NUMBER ?: 'PRIVATE',
            'build.url': System.env.BUILD_URL ?: System.env.USERNAME,
            'build.version': 'versionString',
            'arxml.md5' : FileUtil.md5(file("./build/out/${component_name}.arxml"))
    ]
}
</code></pre>