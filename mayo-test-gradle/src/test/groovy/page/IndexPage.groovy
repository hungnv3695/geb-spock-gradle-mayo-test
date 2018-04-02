package page

import geb.Page

class IndexPage extends Page{
	static url = "http://ec2-52-197-32-63.ap-northeast-1.compute.amazonaws.com/index.php"
	static at = {title == "TOP ページ | 丸の内よろずネットショップ"}
	static content = {
		normal_product {$("ul.featured_products__grid li div.module_element_type-product > a", 0)}
		regular_product {$("a", href: endsWith('249'))}
	}
}
