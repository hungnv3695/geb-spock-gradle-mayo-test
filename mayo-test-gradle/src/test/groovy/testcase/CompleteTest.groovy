package testcase

import geb.spock.GebReportingSpec
import page.*

class CompleteTest extends GebReportingSpec {
	
	def setup() {
		browser.config.autoClearCookies = false
	}
	
	def "Complete screen: back to top page" () {
		given:
		to LoginPage
		
		when:
		mail_address = "sogo.takano@bigtreetc.com"
		password = "testtest"
		login_btn.click()
		at IndexPage
		normal_product.click()
		
		then:
		assert waitFor{at ProductDetailPage}
		
		when:
		add_to_cart_btn.click()
		
		then:
		assert waitFor{at CartPage}
		
		when:
		to_purchase_procedure_btn.click()
		
		then:
		//Start step 1
		assert waitFor{at DeliveryPage}
		assert module_page_end_btn.find("a")*.text() == ["戻る", "次へ"]
		
		def date = new Date()
		when:
		select_delivery_year.selected = date[Calendar.YEAR]
		select_delivery_month.selected = date[Calendar.MONTH] + 2
		select_delivery_day.selected = date[Calendar.DATE] - 2
		select_delivery_time.selected = "1"
		delivery_next_btn.click()
		
		//End step 1
		then:
		//Start step 2
		assert waitFor{at PaymentPage}

		when:
		choice_radio_1.checked = "11"
		
		then:
		choice_radio_1.checked == "11"
		
		when:
		payment_next_btn.click()
		
		then:
		assert waitFor{at ConfirmPage}
		
		when:
		confirm_complete_btn.click()
		
		then:
		assert waitFor{at CompletePage}
		
		when:
		complete_back_btn.click()
		
		then:
		assert waitFor{at IndexPage}
	}
	//complete screen end
	def "add new credit card" () {
		given:
		to IndexPage
		when:
		normal_product.click()
		
		then: "go to Produc Detail page"
		assert waitFor{at ProductDetailPage}
		
		when: "click button add to cart"
		add_to_cart_btn.click()
		
		then: "go to cart page"
		assert waitFor{at CartPage}
		
		when:
		to_purchase_procedure_btn.click()
		
		then:
		//Start step 1
		assert waitFor{at DeliveryPage}
		assert stack_title.text() == "ご依頼主・お届け先を設定する"
		
		def date = new Date()
		when:
		select_delivery_year.selected = date[Calendar.YEAR]
		select_delivery_month.selected = date[Calendar.MONTH] + 2
		select_delivery_day.selected = date[Calendar.DATE] - 2
		select_delivery_time.selected = "1"
		delivery_next_btn.click()

		then:
		assert waitFor{at PaymentPage}

		when: "set add new card"
		add_card_radio.checked = "-1"
		
		then: "click submit to go to add new card page"
		assert waitFor{ add_card_radio.checked == "-1" }
		payment_next_btn.click()
		at SelectCardPage
		
		when: "set card info"
		card_number = "2111111111111111"
		expire_year.selected = "2020"
		expire_month.selected = "12"
		
		then: "check info card"
		card_number == "2111111111111111"
		expire_year.selectedText == "2020"
		expire_month.selectedText == "12"
		
		when: "click next button"
		next_btn.click()
		
		then: "go to confirm page"
		assert waitFor{at ConfirmPage}
	}
	
	def cleanupSpec() {
        browser.clearCookies()
	}
}
