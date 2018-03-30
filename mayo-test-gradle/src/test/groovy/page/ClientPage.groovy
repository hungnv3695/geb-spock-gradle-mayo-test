package page

import geb.Page

class ClientPage extends Page{
	static url = "http://ec2-52-197-32-63.ap-northeast-1.compute.amazonaws.com/order/client.php"
	static at = {title == "ご依頼主情報 | 丸の内よろずネットショップ"}
	static content = {
		
	}
}
