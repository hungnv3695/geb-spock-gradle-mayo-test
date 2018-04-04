package testcase

import geb.spock.GebReportingSpec
import page.*
import spock.lang.*
@Ignore
class ConfirmTest extends GebReportingSpec {
	
	def setup() {
		browser.config.autoClearCookies = false
	}
	
	def "Confirm screen: next button" () {
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
		//assert module_page_end_btn.find("a")*.text() == ["戻る", "次へ"]
		def date = new Date()
		when:
		select_delivery_year.selected = date[Calendar.YEAR]
		select_delivery_month.selected = date[Calendar.MONTH] + 2
		select_delivery_day.selected = date[Calendar.DATE]
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
	}
	
	def "Confirm screen: back button" () {
		given:
		to IndexPage
		when:
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
		select_delivery_day.selected = date[Calendar.DATE]
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
		confirm_back_btn.click()
		
		then:
		assert waitFor{at PaymentPage}
	}
	
	def cleanupSpec() {
        browser.clearCookies()
	}
}
