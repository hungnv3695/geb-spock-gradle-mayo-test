package testcase

import geb.spock.GebReportingSpec
import page.*

class SelectCard extends GebReportingSpec {
	
	def setup() {
		browser.config.autoClearCookies = false
	}
	
	def "Select cart screen: back button"() {
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
		choice_radio_1.checked = "-1"
		
		then:
		choice_radio_1.checked == "-1"
		
		when:
		payment_next_btn.click()
		
		then:
		assert waitFor{at SelectCardPage}
		
		when:
		back_btn.click()
		
		then:
		assert waitFor{at PaymentPage}
	}
	
	def "Select cart screen: next button"() {
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
		select_delivery_day.selected = date[Calendar.DATE] - 2
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
		
		when:
		card_number = "5555555555554444"
		expire_year.selected = "2018"
		expire_month.selected = "1"
		next_btn.click()
		
		then:
		assert waitFor{at ConfirmPage}
	}
	
	def "Select cart screen: next button with no input card's info"() {
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
		select_delivery_day.selected = date[Calendar.DATE] - 2
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
		
		when:
		card_number = ""
		expire_year.selected = ""
		expire_month.selected = ""
		next_btn.click()
		
		then:
		assert card_no_error.text() == "※カード番号は必須項目です。"
		assert expire_year_error.text() == "※有効期限：「年」は必須項目です。"
		assert expire_month_error.text() == "※有効期限：「月」は必須項目です。"
		assert waitFor{at SelectCardPage}
	}
	
	def cleanupSpec() {
        browser.clearCookies()
	}	
}
