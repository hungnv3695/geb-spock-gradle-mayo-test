package testcase

import geb.spock.GebReportingSpec
import page.IndexPage
import page.LoginPage

class LoginTest extends GebReportingSpec {
    def "Login with correct infomation" () {
        given: "Go to login page"
        to LoginPage

        //Step1: Enter email,password, click button Login
        when: "Input correct login info, click button Login"
        mail_address = "sogo.takano@bigtreetc.com"
        password = "testtest"
        login_btn.click()

        then: "Direct to Index Page"
        at waitFor {IndexPage}
        assert status_bar__user_name.text() == "ようこそ 高野 宗吾 様"
    }

    def "Login with correct infomation but display name in top page incorrect" () {
        given: "Go to login page"
        to LoginPage

        //Step1: Enter email,password, click button Login
        when: "Input correct login info, click button Login"
        mail_address = "sogo.takano@bigtreetc.com"
        password = "testtest"
        login_btn.click()

        then: "Direct to Index Page"
        at waitFor {IndexPage}
        assert status_bar__user_name.text() == "ようこそ タカノソゴ 様"
    }
}
