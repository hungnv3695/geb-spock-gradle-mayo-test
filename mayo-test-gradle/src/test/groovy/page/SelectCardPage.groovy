package page

import geb.Page
import geb.module.Select

class SelectCardPage extends Page {
	static url = "http://ec2-52-197-32-63.ap-northeast-1.compute.amazonaws.com/order/select_card.php"
	static at = {title == "新しいクレジットカード情報の入力 | 丸の内よろずネットショップ"}
	static content = {
		card_number {$("input[name=cardNoTextBox]")}
		expire_year {$("select[name=expire_year]").module(Select)}
		expire_month {$("select[name=expire_month]").module(Select)}
		
		card_no_error {$("span.error#cardNo")}
		expire_year_error {$("span.error#expire_year")}
		expire_month_error {$("span.error#expire_month")}
		
		next_btn {$(".module_page-end a", text: contains('次へ'))}
		back_btn {$(".module_page-end a", text: contains('戻る'))}
	}
}
