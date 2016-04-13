# PublishArtifactMetadataGradlePlugin
This gradle project has a class update artifact properties

Usage:

Clone this repo
git clone ...
Got to git repository
Build project and publish it to MavenLocal repository by command:
./gradlew publishMavenPublicationToMavenLocal

You can check if *.jar has been published in
~/.m2/repository/com/praqma/artifact-metadata/0.1.0

In build.gradle file of a project you want to use artifact-metadata classes
add this code:

<pre><code>
buildscript {
    repositories {
        mavenLocal()
    }
    dependencies {
       classpath "com.praqma:artifact-metadata:0.1.0"
    }
}

import com.praqma.PropertiesGenerator

def props = PropertiesGenerator.updatePropertyMap(filepath, version)
</code></pre>
