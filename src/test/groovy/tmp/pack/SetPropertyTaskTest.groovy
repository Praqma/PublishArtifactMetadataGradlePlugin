package tmp.pack


class SetPropertyTaskTest extends GroovyTestCase {

    void testGetPropertiesPath() {
        def path = SetPropertyTask.getPropertiesPath('https://dummy.url', 'dummyRepo', 'dummyPath')
        assert "https://dummy.url/api/storage/dummyRepo/dummyPath" == path
    }

    void testCreateSetPropertiesURL() {
        def propertiesMap = ['valueA': 'A', 'valueB': 'B']
        def path = "https://dummy.url/api/storage/dummyRepo/dummyPath"
        assert "https://dummy.url/api/storage/dummyRepo/dummyPath?properties=valueA=A|valueB=B" ==
                SetPropertyTask.createSetPropertiesURL(path, propertiesMap)
    }

    /**
     * In order to supply special characters (comma (,), backslash(\), pipe(|), equals(=))
     * as key/value you must add a backslash (\) before them.
     */
    void testCreateSetPropertiesURLWithEscape() {
        def propertiesMap = ['comma': ',', 'backslash': '\\', 'pipe': '|', 'equals': '=']
        def path = "https://dummy.url/api/storage/dummyRepo/dummyPath"
        assert 'https://dummy.url/api/storage/dummyRepo/dummyPath?properties=comma=\\,|backslash=\\\\|pipe=\\||equals=\\=' ==
                SetPropertyTask.createSetPropertiesURL(path, propertiesMap)
    }

    void testCreateGetPropertiesURL() {
        def path = "https://dummy.url/api/storage/dummyRepo/dummyPath"
        assert "https://dummy.url/api/storage/dummyRepo/dummyPath?properties" ==
                SetPropertyTask.createGetPropertiesURL(path)
    }
}
