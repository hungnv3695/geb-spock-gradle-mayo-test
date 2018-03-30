package page

import geb.Page

class LoginPage extends Page{
	static url = "http://ec2-52-197-32-63.ap-northeast-1.compute.amazonaws.com/mypage/login.php"
	static at = {title == "ログイン | 丸の内よろずネットショップ"}
	static content = {
		mail_address {$("input[name=mypage_login_email]")}
		password {$("input[name=mypage_login_pass]")}
		login_btn {$("button.module_page-end__positive.module_btn_big--primary")}
	}
}
