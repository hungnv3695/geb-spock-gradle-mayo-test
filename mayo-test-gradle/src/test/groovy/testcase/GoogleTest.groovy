package testcase

import geb.spock.GebReportingSpec
import page.*
import page.GooglePage
import spock.lang.Ignore

@Ignore
class GoogleTest  extends GebReportingSpec{
    def "google test"() {
        given:
        to GooglePage

        when:
        textbox = "aaaaaaaa"

        then:
        assert textbox == "aaaaaaaa"
    }
}
