package com.praqma


class SetPropertyTaskTest extends GroovyTestCase {

    void testGetPropertiesPath() {
        def path = SetPropertyTask.getPropertiesPath('dummyRepo', 'dummyPath')
        assert "/api/storage/dummyRepo/dummyPath" == path
    }

    void testCreateSetPropertiesValue() {
        def propertiesMap = ['valueA': 'A', 'valueB': 'B']
        assert "valueA=A|valueB=B" ==
                SetPropertyTask.createSetPropertiesValue(propertiesMap)
    }

    /**
     * In order to supply special characters (comma (,), backslash(\), pipe(|), equals(=))
     * as key/value you must add a backslash (\) before them.
     */
    void testCreateSetPropertiesURLWithEscape() {
        def propertiesMap = ['comma': ',', 'backslash': '\\', 'pipe': '|', 'equals': '=']
        assert 'comma=\\,|backslash=\\\\|pipe=\\||equals=\\=' ==
                SetPropertyTask.createSetPropertiesValue(propertiesMap)
    }
}
