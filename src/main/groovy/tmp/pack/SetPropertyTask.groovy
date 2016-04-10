package tmp.pack

import groovy.json.JsonSlurper
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method
import groovyx.net.http.RESTClient
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

import static groovyx.net.http.ContentType.JSON
import static groovyx.net.http.Method.GET
import static groovyx.net.http.Method.PUT
import static groovyx.net.http.ContentType.TEXT

/**
 *  https://www.jfrog.com/confluence/display/RTF/Artifactory+REST+API
 *
 *  PUT /api/storage/{repoKey}{itemPath}?properties=p1=v1[,v2][|p2=v3][&recursive=1]
 *  repoKey = ${artifactory_repoPublish}
 *  itemPath = gtt/test/vm/$component_name/$versionString
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
        def http = new HTTPBuilder(url)

        http.handler.failure = { resp ->
            println "Unexpected failure: ${resp.statusLine}"
        }

        http.request(PUT, TEXT) {
            uri.path = getPropertiesPath(repoKey, itemPath)
            uri.query = ["properties": createSetPropertiesValue(propertiesMap)]
        }

        http.request(GET, JSON) {
            uri.path = getPropertiesPath(repoKey, itemPath)

            response.success = { resp, json ->
                println "Query response: $json"
            }
        }
        // TODO: A possible improvement is verifying all properties are set.
        // Do GET request and assert the properties are matching expected.
    }

    static String createSetPropertiesValue(Map<String, String> propertiesMap) {
        propertiesMap
                .collect { it -> "${escape(it.key)}=${escape(it.value)}" }
                .join('|')
    }

    static String getPropertiesPath(String repoKey, String itemPath) {
        "/api/storage/$repoKey/$itemPath"
    }

    static String escape(String string) {
        string.replace('\\', '\\\\')
                .replace(',', '\\,')
                .replace('|', '\\|')
                .replace('=', '\\=')
    }
}
