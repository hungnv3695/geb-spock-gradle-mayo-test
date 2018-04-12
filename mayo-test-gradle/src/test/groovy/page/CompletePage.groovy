package page

import geb.Page

class CompletePage extends Page{
	static url = "http://ec2-52-197-32-63.ap-northeast-1.compute.amazonaws.com/order/complete.php"
	static at = {title == "ご購入手続き | 丸の内よろずネットショップ"}
	static content = {
		thankyou_text {$(".inner_body h6")}
		confirm_page_note {$(".inner_body p")}
		complete_back_btn {$("a.module_page-end__negative.module_btn_big")}

	}
}
