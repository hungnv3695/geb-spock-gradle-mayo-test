package testcase

import geb.spock.GebReportingSpec
import page.*
import page.CartPage

class DeliveryTest extends GebReportingSpec {
	
	def setup() {
		browser.config.autoClearCookies = false
	}
	
	def "Delivery screen: check function button" () {
		given: "go to login page"
		to LoginPage
		
		when: "input username and pwd"
		mail_address = "sogo.takano@bigtreetc.com"
		password = "testtest"
		login_btn.click()
		at IndexPage
		normal_product.click()
		
		then: "go to Product Detail page"
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
		
		when:
		change_customer_btn.click()
		
		then:
		assert waitFor{at SelectAddressPage}
		
		when:
		back_btn.click()
		
		then:
		assert waitFor{at DeliveryPage}
		assert delivery_setting_radio.checked == "1"
		assert div_same_delivery_address.displayed
		
		when:
		change_address_btn.click()
		
		then:
		title == "アドレス帳選択［お届け先情報］ | 丸の内よろずネットショップ"
	}
	
	def "Delivery screen: select specify addressee for each item" () {
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
		
		when:
		delivery_setting_radio.checked = "0"
		
		then:
		assert waitFor{div_same_delivery_address.displayed == false}
		assert waitFor{div_each_item_delivery_address.displayed}
	}
	
	def "Delivery screen: don't input year-month-day and time" () {
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
		
		when: "set value for datetime select box"
		select_delivery_year.selected = ""
		select_delivery_month.selected = ""
		select_delivery_day.selected = ""
		select_delivery_time.selected = ""
		
		then:
		select_delivery_year.selected == ""
		select_delivery_month.selected == ""
		select_delivery_day.selected == ""
		select_delivery_time.selected == ""
		
		when:
		delivery_next_btn.click()
		
		then: "reload delivery page"
		assert waitFor{at DeliveryPage}
	}
	
	def cleanupSpec() {
        browser.clearCookies()
	}
}