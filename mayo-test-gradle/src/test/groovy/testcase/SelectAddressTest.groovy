package testcase

import geb.spock.GebReportingSpec
import page.*
import page.CartPage

class SelectAddressTest extends GebReportingSpec {
	def setup() {
		browser.config.autoClearCookies = false
	}
	
	def setupSpec() {
		given: "go to login page"
		to LoginPage
		
		when: "input username and pwd"
		mail_address = "sogo.takano@bigtreetc.com"
		password = "testtest"
		login_btn.click()
		
		then:
		at IndexPage
	}
	
	def "Select address page: test button go to add new addressee page" () {
		given: "go to index page"
		to IndexPage
		
		when: "choose one normal product"
		normal_product.click()
		
		then: "go to Produc Detail page"
		assert waitFor{at ProductDetailPage}
		
		when: "click button add to cart"
		add_to_cart_btn.click()
		
		then: "go to cart page"
		assert waitFor{at CartPage}
		
		when: "click button go to delivery page"
		to_purchase_procedure_btn.click()
		
		then: "location at delivery page"
		//Start step 1
		assert waitFor{at DeliveryPage}
		
		when: "click button change customer"
		change_customer_btn.click()
		
		then: "location at select customer address page"
		assert waitFor{at SelectAddressPage}
		
		when: "click add new addressee button"
		add_new_addressee_btn.click()
		
		then: "location at client page"
		assert waitFor{at ClientPage}
	}
	
	def "Select address page: edit addressee" () {
		given: "go to index page"
		to IndexPage
		
		when: "choose one normal product"
		normal_product.click()
		
		then: "go to Produc Detail page"
		assert waitFor{at ProductDetailPage}
		
		when: "click button add to cart"
		add_to_cart_btn.click()
		
		then: "go to cart page"
		assert waitFor{at CartPage}
		
		when: "click button go to delivery page"
		to_purchase_procedure_btn.click()
		
		then: "location at delivery page"
		//Start step 1
		assert waitFor{at DeliveryPage}
		
		when: "click button change customer"
		change_customer_btn.click()
		
		then: "location at select customer address page"
		assert waitFor{at SelectAddressPage}
		
		when:
		edit_addressee_btn.click()
		
		then:
		assert waitFor{at ClientPage}
	}
	
	def "Select address page: select another addressee" () {
		given: "go to index page"
		to IndexPage
		
		when: "choose one normal product"
		normal_product.click()
		
		then: "go to Produc Detail page"
		assert waitFor{at ProductDetailPage}
		
		when: "click button add to cart"
		add_to_cart_btn.click()
		
		then: "go to cart page"
		assert waitFor{at CartPage}
		
		when: "click button go to delivery page"
		to_purchase_procedure_btn.click()
		
		then: "location at delivery page"
		//Start step 1
		assert waitFor{at DeliveryPage}
		
		when: "click button change customer"
		change_customer_btn.click()
		
		then: "location at select customer address page"
		assert waitFor{at SelectAddressPage}
		
		when:
		select_addressee_btn.click()
		
		then:
		assert waitFor{at DeliveryPage}
	}
	
	def cleanupSpec() {
		browser.clearCookies()
	}
}
