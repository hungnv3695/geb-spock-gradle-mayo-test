package page

import geb.Page
import module.ProgressElementModule
import geb.module.RadioButtons

class PaymentPage extends Page {
	static url = "http://ec2-52-197-32-63.ap-northeast-1.compute.amazonaws.com/order/payment.php"
	static at = {title == "クレジットカードの選択 | 丸の内よろずネットショップ"}
	static content = {
		progress_element { module ProgressElementModule }
		
		procedure_title {$("div.inner h4")}
		head_h5 {$(".head h5")}
		head_p {$(".head p")}
		
		boxlayout_grid_elements {$("ul.boxlayout__grid li.boxlayout__element")}
		
		choice_radio_1 {$("input[name=selected_card]").module(RadioButtons)}
		add_card_radio {$("input[name=selected_card]").module(RadioButtons)}
		
		submit_btn {$("div.module_page-end")}
		payment_next_btn {submit_btn.find("a:nth-child(2)")}
		payment_back_btn {submit_btn.find("a:nth-child(1)")}
		
	}
}
