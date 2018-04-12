package page

import geb.Page

class IndexPage extends Page{
	static url = "http://ec2-52-197-32-63.ap-northeast-1.compute.amazonaws.com/index.php"
	static at = {title == "TOP ページ | 丸の内よろずネットショップ"}
	static content = {
		status_bar__user_name {$(".status_bar__user_name")}
		normal_product {$("ul.featured_products__grid li div.module_element_type-product > a", 0)}
		regular_product {$("a", href: endsWith('249'))}
		index_page_menu {$("div.menu div.inner a.menu__item")}
		featured_products_title {$(".featured_products h4")}
		featured_service_title {$(".featured_service h4")}
		featured_partner_title {$(".featured_partner h4")}
		ranking_products_title {$(".ranking_products h4")}
	}
}
