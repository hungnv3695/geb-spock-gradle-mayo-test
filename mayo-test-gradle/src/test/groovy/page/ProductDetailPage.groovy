package page

import geb.Page

class ProductDetailPage extends Page {
	static url = "/products/detail.php"
	static at = {title == "商品詳細画面 | 丸の内よろずネットショップ"}
	static content = {
		add_to_cart_btn {$("div.product__detail__add > a#cart-btn")}
	}
}
