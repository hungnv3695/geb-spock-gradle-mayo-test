package page

import geb.Page
import module.ProgressElementModule
import geb.module.Select
import geb.module.RadioButtons

class DeliveryPage extends Page {
	static url = "http://ec2-52-197-32-63.ap-northeast-1.compute.amazonaws.com/order/delivery_addr.php"
	static at = {title == "ご購入手続き | 丸の内よろずネットショップ"}
	static content = {
		procedure_title {$("div.inner h4")}
		stack_title {$("section.stacking div.inner h5")}
		
		stacking__element_1 {$("section.stacking div.inner dl.stacking__element", 0)}
		stacking__element_1_title {stacking__element_1.find("dt", 0)}
		stacking__element_1_label {stacking__element_1.find("dd label")}
		
		stacking__element_2 {$("section.stacking div.inner dl.stacking__element", 1)}
		stacking__element_2_title {stacking__element_2.find("dt", 0)}
		stacking__element_2_button {stacking__element_2.find("dd a")}
		
		section_delivery_address_1 {$("div#div_same_delivery_address > section#div_same_delivery_address", 0)}
		section_delivery_address_2 {$("div#div_same_delivery_address > section#div_same_delivery_address", 1)}
		
		section_delivery_address_1_title {section_delivery_address_1.find("dt")}
		section_delivery_address_1_button {section_delivery_address_1.find("a")}
		
		section_delivery_address_2_title {section_delivery_address_2.find("dt")}
		
		section_delivery_date_label {section_delivery_address_2.find(".module_date__label", 0)}
		section_delivery_year_label {section_delivery_address_2.find(".module_date__nowrap span.module_date__label")}
		
		select_delivery_year {section_delivery_address_2.find(".module_date__nowrap select", name: endsWith('year')).module(Select)}
		select_delivery_month {section_delivery_address_2.find(".module_date__nowrap select", name: endsWith('month')).module(Select)}
		select_delivery_day {section_delivery_address_2.find(".module_date__nowrap select", name: endsWith('day')).module(Select)}
		select_delivery_time {$("select", name: startsWith('s_time_period_')).module(Select)}
		
		module_page_end_btn {$("div.module_page-end")}
		delivery_back_btn {module_page_end_btn.find("a", text: contains('戻る'))}
		delivery_next_btn {module_page_end_btn.find("a", text: contains('次へ'))}
		change_customer_btn {$("a", text: contains("ご依頼主を変更する"))}
		change_address_btn {$("a", text: contains ("お届け先を変更する"))}
		
		delivery_setting_radio {$(".stacking__element__body label input").module(RadioButtons)}
		
		div_same_delivery_address {$("div#div_same_delivery_address")}
		div_each_item_delivery_address {$("div#div_each_item_delivery_address")}
		
		date_error_msg {$(".add span.error")}
		
		progress_element { module ProgressElementModule }
	}
}
