package com.praqma

class PropertiesGenerator {
    public static Map<String, String> updatePropertyMap(String filePath, String versionString) {
        [
                'build.name'   : System.env.JOB_NAME ?: System.env.HOSTNAME,
                'build.number' : System.env.BUILD_NUMBER ?: 'PRIVATE',
                'build.url'    : System.env.BUILD_URL ?: System.env.USERNAME,
                'build.version': versionString,
                'arxml.md5'    : FileUtil.md5(new File(filePath))
        ]
    }

}
