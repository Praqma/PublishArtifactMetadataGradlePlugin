package com.praqma

class FileUtilTest extends GroovyTestCase {
    void testGetProperties() {
        assert '74b1e7e8e47d82b4faafcdbaed646b9a' ==
                FileUtil.md5(new File("src/test/resources/somefile.txt"))
    }
}
