package testcase

import geb.spock.GebReportingSpec
import page.*
import spock.lang.*

class CompleteTest extends GebReportingSpec {
	
	def setup() {
		browser.config.autoClearCookies = false
	}

	def "Complete screen: order product flow" () {
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

		//Step2:
		when: "Select normal product"
		normal_product.click()

		then: "Location at Product Detail Page"
		assert waitFor { at ProductDetailPage }

		//Step3:
		when: "Click button add to cart"
		add_to_cart_btn.click()

		then: "Location at Cart Page"
		assert waitFor { at CartPage }

		//Step4
		when: "Click button to go to perchase procedure"
		to_purchase_procedure_btn.click()


		then: "Location at Delivery Page and check object's text in screen"
		assert waitFor { at DeliveryPage }
		assert stack_title.text() == "ご依頼主・お届け先を設定する"
		assert stacking__element_1_title.text() == "配送設定"
		assert stacking__element_2_title.text() == "ご依頼主"

		//Step5
		when: "Click button to go to payment page"
		/*select_delivery_year.selected = date[Calendar.YEAR]
		select_delivery_month.selected = date[Calendar.MONTH] + 1
		select_delivery_day.selected = date[Calendar.DATE]
		select_delivery_time.selected = "1"*/
		delivery_next_btn.click()

		then: "Location at Payment page and check object's text in screen"


		assert waitFor { at PaymentPage }
		assert head_h5.text() == "クレジットカードの選択"
		assert head_p.text() == "使用するカードを選択し、「次へ」ボタンを押してください。"

		//Step6
		when: "Choose card to payment"
		choice_radio_1.checked = "11"

		then: "Check card chose"
		assert choice_radio_1.checked == "11"

		//Step7
		when: "Click button to go to Confirm page"
		payment_next_btn.click()

		then: "Location at confirm page and check object's text in screen"
		assert waitFor { at ConfirmPage }
		assert confirm_page_header.text() == "入力内容の確認"
		assert confirm_page_note.text() == "注文内容をご確認いただき、「ご注文を完了する」ボタンを押してください。"

		//Step8
		when: "Click button to go to Complete page"
		confirm_complete_btn.click()

		then: "Location at Complete page and check object's text in screen"
		assert waitFor { at CompletePage }
		assert thankyou_text.text() == "ご注文ありがとうございました。"
		assert confirm_page_note.text() == "当ネットショップをご利用いただきまして誠にありがとうございます。\nご登録のメールアドレスに「ご注文確認メール」をお送りいたしました。"

		//Step9: bỏ đoạn này đi nhé
		/*when: "Click button complete to go to Index page"
		complete_back_btn.click()

		then: "Location at Index page"
		assert waitFor{at IndexPage}*/
	}
	//complete screen end
    /*@Ignore
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
*//*		select_delivery_year.selected = date[Calendar.YEAR]
		select_delivery_month.selected = date[Calendar.MONTH] + 2
		select_delivery_day.selected = date[Calendar.DATE]
		select_delivery_time.selected = "1"*//*
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
	}*/
	
	def cleanupSpec() {
        browser.clearCookies()
	}
}
