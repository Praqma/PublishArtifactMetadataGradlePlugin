package tmp.pack

import groovy.json.JsonSlurper
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

/**
 *  https://www.jfrog.com/confluence/display/RTF/Artifactory+REST+API
 *
 *  PUT /api/storage/{repoKey}{itemPath}?properties=p1=v1[,v2][|p2=v3][&recursive=1]
 *  repoKey = ${artifactory_repoPublish}*  itemPath = gtt/test/vm/$component_name/$versionString
 */
class SetPropertyTask extends DefaultTask {

    @Input
    Map<String, String> propertiesMap

    @Input
    String url

    @Input
    String repoKey

    @Input
    String itemPath

    @TaskAction
    public void setProperties() {
        def path = getPropertiesPath(url, repoKey, itemPath)
        ['curl', '-X', 'PUT', "\"${createSetPropertiesURL(path, propertiesMap)}\""].execute()
        def proc = ['curl', "\"${createGetPropertiesURL(path)}\""].execute()
        println new JsonSlurper().parseText(proc.text)
    }

    static String createSetPropertiesURL(String path, Map<String, String> propertiesMap) {
        def propertiesValues = propertiesMap
                .collect { it -> "${escape(it.key)}=${escape(it.value)}" }
                .join('|')
        "$path?properties=$propertiesValues"
    }

    static String createGetPropertiesURL(String path) {
        "$path?properties"
    }

    static String getPropertiesPath(String url, String repoKey, String itemPath) {
        "$url/api/storage/$repoKey/$itemPath"
    }

    static String escape(String string) {
        string.replace('\\', '\\\\')
                .replace(',', '\\,')
                .replace('|', '\\|')
                .replace('=', '\\=')
    }
}
