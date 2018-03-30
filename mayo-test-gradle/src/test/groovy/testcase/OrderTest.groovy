package testcase
/*package test.groovy.testcase

import geb.spock.GebReportingSpec
import test.groovy.page.LoginPage
import test.groovy.page.PaymentPage
import test.groovy.page.IndexPage
import test.groovy.page.ProductDetailPage
import test.groovy.page.CartPage
import test.groovy.page.DeliveryPage
import test.groovy.module.ProgressElementModule
import test.groovy.page.ConfirmPage
import test.groovy.page.CompletePage
import test.groovy.page.*

class OrderTest extends GebReportingSpec{
	def "test buy product" () {
		given:
		to LoginPage
		
		when:
		mail_address = "sogo.takano@bigtreetc.com"
		password = "testtest"
		login_btn.click()
		at IndexPage
		product.click()
		
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
		
		assert procedure_title.text() == "ご購入手続き"
		assert progress_element.element1_p1.text() == "STEP1"
		assert progress_element.element1_p2.text() == "配送指定"
		assert progress_element.element1_p1.jquery.css("background-color") == "rgb(206, 18, 6)"
		
		assert progress_element.element2_p1.text() == "STEP2"
		assert progress_element.element2_p2.text() == "お支払い方法"
		
		assert progress_element.element3_p1.text() == "STEP3"
		assert progress_element.element3_p2.text() == "入力内容確認"
		
		assert progress_element.element4_p1.text() == "STEP4"
		assert progress_element.element4_p2.text() == "ご注文完了"
		
		assert stack_title.text() == "ご依頼主・お届け先を設定する"
		
		assert stacking__element_1_title.text() == "配送設定"
		assert stacking__element_1_label*.text() == ["お届け先を一括で指定する", "商品ごとにお届け先を指定する"]
		
		assert stacking__element_2_title.text() == "ご依頼主"
		assert stacking__element_2_button.text() == "ご依頼主を変更する"
		
		assert section_delivery_address_1_title.text() == "お届け先"
		assert section_delivery_address_1_button.text() == "お届け先を変更する"
		
		assert section_delivery_address_2_title*.text() == ["配送希望日", "お届け時間帯", "備考"]
		assert section_delivery_date_label.text() == "西暦"
		assert section_delivery_year_label*.text() == ["年", "月", "日"]
		assert module_page_end_btn.find("a")*.text() == ["戻る", "次へ"]
		
		when:
		select_delivery_year.selected = "2018"
		select_delivery_month.selected = "3"
		select_delivery_day.selected = "26"
		select_delivery_time.selected = "1"
		delivery_next_btn.click()
		
		//End step 1
		then:
		//Start step 2
		assert waitFor{at PaymentPage}
		
		assert procedure_title.text() == "ご購入手続き"
		assert progress_element.element1_p1.text() == "STEP1"
		assert progress_element.element1_p2.text() == "配送指定"
		
		assert progress_element.element2_p1.text() == "STEP2"
		assert progress_element.element2_p2.text() == "お支払い方法"
		assert progress_element.element2_p1.jquery.css("background-color") == "rgb(206, 18, 6)"
		
		assert progress_element.element3_p1.text() == "STEP3"
		assert progress_element.element3_p2.text() == "入力内容確認"
		
		assert progress_element.element4_p1.text() == "STEP4"
		assert progress_element.element4_p2.text() == "ご注文完了"
		
		assert head_h5.text() == "クレジットカードの選択"
		assert head_p.text() == "使用するカードを選択し、「次へ」ボタンを押してください。"

		when:
		choice_radio_1.checked = "11"
		
		then:
		choice_radio_1.checked == "11"
		
		when:
		submit_btn.find("a:nth-child(2)").click()
		
		then:
		assert waitFor{at ConfirmPage}
		
		when:
		confirm_complete_btn.click()
		
		then:
		assert waitFor{at CompletePage}
		assert thankyou_text.text() == "ご注文ありがとうございました。"
	}
	
	//cart screen start
	def "Cart screen: check back button" () {
		given: "go to login page"
		to LoginPage
		
		when: "input username and pwd"
		mail_address = "sogo.takano@bigtreetc.com"
		password = "testtest"
		login_btn.click()
		at IndexPage
		product.click()
		
		then: "go to Produc Detail page"
		assert waitFor{at ProductDetailPage}
		
		when: "click button add to cart"
		add_to_cart_btn.click()
		
		then: "go to cart page"
		assert waitFor{at CartPage}
		
		when:
		cart_back_btn.click()
		
		then:
		assert waitFor{at IndexPage}
	}

	def "Cart screen: check recalculation" () {
		given: "go to login page"
		to LoginPage
		
		when: "input username and pwd"
		mail_address = "sogo.takano@bigtreetc.com"
		password = "testtest"
		login_btn.click()
		at IndexPage
		product.click()
		
		then: "go to Produc Detail page"
		assert waitFor{at ProductDetailPage}
		
		when: "click button add to cart"
		add_to_cart_btn.click()
		
		then: "go to cart page"
		assert waitFor{at CartPage}
		
		when:
		//normal_quantity_416.value("2")
		plus_normal_quantity_416.click()
		
		then:
		waitFor{normal_quantity_416 == "2"}
		when:
		recalculation_btn.click()
		
		then:
		assert waitFor{price_416.text() == "12,100"} 
		assert waitFor{cart_normal_total_price_top.text() == "14,100"} 
		assert waitFor{cart_normal_total_price.text() == "12,100"}
		assert waitFor{cart_normal_final_ship_cost.text() == "2,000"}
		assert waitFor{cart_normal_final_total_cost.text() == "14,100"}
		assert waitFor{at CartPage}
	}
	//cart screen end
	
	//delivery screeen start
	def "Delivery screen: check function button" () {
		given: "go to login page"
		to LoginPage
		
		when: "input username and pwd"
		mail_address = "sogo.takano@bigtreetc.com"
		password = "testtest"
		login_btn.click()
		at IndexPage
		product.click()
		
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
		change_customer_btn.click()
		
		then:
		assert waitFor{at SelectAddress}
		
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
		given: "go to login page"
		to LoginPage
		
		when: "input username and pwd"
		mail_address = "sogo.takano@bigtreetc.com"
		password = "testtest"
		login_btn.click()
		at IndexPage
		product.click()
		
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
		given: "go to login page"
		to LoginPage
		
		when: "input username and pwd"
		mail_address = "sogo.takano@bigtreetc.com"
		password = "testtest"
		login_btn.click()
		at IndexPage
		product.click()
		
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
	//delivery screeen end
	
	//payment screen start
	def "Payment screen: next button when choose card" () {
		given:
		to LoginPage
		
		when:
		mail_address = "sogo.takano@bigtreetc.com"
		password = "testtest"
		login_btn.click()
		at IndexPage
		product.click()
		
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
		
		when:
		select_delivery_year.selected = "2018"
		select_delivery_month.selected = "3"
		select_delivery_day.selected = "26"
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
		to LoginPage
		
		when:
		mail_address = "sogo.takano@bigtreetc.com"
		password = "testtest"
		login_btn.click()
		at IndexPage
		product.click()
		
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
		
		when:
		select_delivery_year.selected = "2018"
		select_delivery_month.selected = "3"
		select_delivery_day.selected = "26"
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
		to LoginPage
		
		when:
		mail_address = "sogo.takano@bigtreetc.com"
		password = "testtest"
		login_btn.click()
		at IndexPage
		product.click()
		
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
		
		when:
		select_delivery_year.selected = "2018"
		select_delivery_month.selected = "3"
		select_delivery_day.selected = "26"
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
	//payment screen end
	
	//confirm screen start
	def "Confirm screen: next button" () {
		given:
		to LoginPage
		
		when:
		mail_address = "sogo.takano@bigtreetc.com"
		password = "testtest"
		login_btn.click()
		at IndexPage
		product.click()
		
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
		
		when:
		select_delivery_year.selected = "2018"
		select_delivery_month.selected = "3"
		select_delivery_day.selected = "26"
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
		to LoginPage
		
		when:
		mail_address = "sogo.takano@bigtreetc.com"
		password = "testtest"
		login_btn.click()
		at IndexPage
		product.click()
		
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
		
		when:
		select_delivery_year.selected = "2018"
		select_delivery_month.selected = "3"
		select_delivery_day.selected = "26"
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
	//confirm screen end
	
	//complete screen start
	def "Complete screen: back to top page" () {
		given:
		to LoginPage
		
		when:
		mail_address = "sogo.takano@bigtreetc.com"
		password = "testtest"
		login_btn.click()
		at IndexPage
		product.click()
		
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
		
		when:
		select_delivery_year.selected = "2018"
		select_delivery_month.selected = "3"
		select_delivery_day.selected = "26"
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
		given: "go to login page"
		to LoginPage
		
		when: "input username and pwd"
		mail_address = "sogo.takano@bigtreetc.com"
		password = "testtest"
		login_btn.click()
		at IndexPage
		product.click()
		
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
		select_delivery_year.selected = "2018"
		select_delivery_month.selected = "3"
		select_delivery_day.selected = "26"
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
		next_btn.find("a:nth-child(2)").click()
		
		then: "go to confirm page"
		assert waitFor{at ConfirmPage}
	}
}
*/