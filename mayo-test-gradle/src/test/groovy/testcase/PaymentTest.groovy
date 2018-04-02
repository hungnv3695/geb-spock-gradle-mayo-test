package testcase

import geb.spock.GebReportingSpec
import page.*

class PaymentTest extends GebReportingSpec {
	
	def setup() {
		browser.config.autoClearCookies = false
	}
	
	def "Payment screen: next button when choose card" () {
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
		select_delivery_day.selected = date[Calendar.DATE] - 6
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
	}
	
	def "Payment screen: next button when choose add new card" () {
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
		choice_radio_1.checked = "-1"
		
		then:
		choice_radio_1.checked == "-1"
		
		when:
		payment_next_btn.click()
		
		then:
		assert waitFor{at SelectCardPage}
	}
	
	def "Payment screen: back button comeback delivery screen" () {
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
		payment_back_btn.click()
		
		then:
		assert waitFor{at DeliveryPage}
	}
	
	def cleanupSpec() {
        browser.clearCookies()
	}
}
