package page

import geb.Page

class CartPage extends Page{
	static url = "http://ec2-52-197-32-63.ap-northeast-1.compute.amazonaws.com/order/cart.php"
	static at = {title == "カート | 丸の内よろずネットショップ"}
	static content = {
		form1 (required: false) {$("#form1")}
		
		to_purchase_procedure_btn {$(".module_page-end a.module_page-end__positive")}
		cart_back_btn {$(".module_page-end").find("a", text: contains('お買い物に戻る'))}
		recalculation_btn {$(".module_element_data__total button.module_btn", text: contains('再計算'))}
		delete_product_btn {$("button.module_btn", text: contains('削除'))}
		
		normal_quantity {$("input.quantity_number")}
		
		plus_normal_quantity {$("div.module_numeric-stepper__btns button", id: startsWith("plus_normal"))}
		minus_normal_quantity {$("button", id: startsWith("minus_normal"))}
		cart_normal_total_price_top {$("#cartNormalServiceTotalPriceTop")}
		cart_normal_total_price {$("#cartNormalServiceTotalPrice")}
		cart_normal_final_ship_cost {$("#cartNormalServiceFinalShipCost")}
		cart_normal_final_total_cost {$("#cartNormalServiceFinalTotalCost")}
		
		modal_delete_confirm {$("#general-modal")}
		modal_cancel_btn {$("#general-modal-button-cancel")}
		modal_ok_btn {$("#general-modal-button-ok")}
		
		price {$("label", id: startsWith("price_"))}
		
		label1 {$("h4").text()}
	}
}
