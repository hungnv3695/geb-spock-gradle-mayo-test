package page

import geb.Page

class SelectAddressPage extends Page {
	static url = "http://ec2-52-197-32-63.ap-northeast-1.compute.amazonaws.com/order/select_addr.php"
	static at = {title == "アドレス帳選択［ご依頼主情報］ | 丸の内よろずネットショップ"}
	static content = {
		back_btn {$("a", text:contains("戻る"))}
		add_new_addressee_btn {$("div.address_list__btn a")}
		edit_addressee_btn {$("dd.supple-col a", text: contains('編集する'), 1)}
		select_addressee_btn {$("dd.supple-col a", text: contains('選択する'), 0)}
	}
}
