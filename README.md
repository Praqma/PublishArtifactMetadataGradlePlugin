# PublishArtifactMetadataGradlePlugin
This gradle project has a class update artifact properties

Usage:

<pre><code>   buildscript {
                repositories {
                  maven {
                      ...
                   }
                 }
                 dependencies {
                      classpath "com.praqma:artifact-metadata:0.1.0"
                 }
              }

             import com.praqma.PropertiesGenerator

             def props = PropertiesGenerator.updatePropertyMap(filepath, version)
</code></pre>
