package com.praqma

class PropertiesGeneratorTest extends GroovyTestCase {
    public void testUpdatePropertyMap() throws Exception {
        Map<String, String> propertiesMap = PropertiesGenerator.updatePropertyMap("src/test/resources/somefile.txt", "1.0.1")
        assert propertiesMap['build.version'] == '1.0.1'
        assert propertiesMap['arxml.md5'] == '74b1e7e8e47d82b4faafcdbaed646b9a'
    }
}