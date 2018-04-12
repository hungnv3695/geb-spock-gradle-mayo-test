package page

import geb.Page

class ConfirmPage extends Page {
	static url = "http://ec2-52-197-32-63.ap-northeast-1.compute.amazonaws.com/order/confirm.php"
	static at = {title == "入力内容の確認 | 丸の内よろずネットショップ"}
	static content = {
		confirm_page_header {$("h5")}
		confirm_page_note {$(".inner_body p", 0)}

		confirm_complete_btn {$(".module_page-end a:nth-child(2)")}
		confirm_back_btn {$(".module_page-end a:nth-child(1)")}
	}
}
