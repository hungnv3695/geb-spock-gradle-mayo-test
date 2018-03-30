package testcase

import geb.spock.GebReportingSpec
import spock.lang.*
import page.*

class CartTest extends GebReportingSpec {

    def setup() {
        browser.config.autoClearCookies = false
    }

    def "Cart screen: check back button" () {
        given: "go to login page"
        to LoginPage

        when: "input username and pwd"
        mail_address = "sogo.takano@bigtreetc.com"
        password = "testtest"
        login_btn.click()
        at IndexPage
        normal_product.click()

        then: "go to Produc Detail page"
        assert waitFor{at ProductDetailPage}

        when: "click button add to cart"
        add_to_cart_btn.click()

        then: "go to cart page"
        assert waitFor{at CartPage}

        when: "click back button in cart screen"
        cart_back_btn.click()

        then: "location at indexPage now"
        assert waitFor{at IndexPage}
    }
    @Ignore
    def "Cart screen: check to go purchase procedure button" () {
        given: "go to index page"
        to IndexPage
        when: "choose one normal product"
        normal_product.click()

        then: "go to Product Detail page"
        assert waitFor{at ProductDetailPage}

        when: "click button add to cart"
        add_to_cart_btn.click()

        then: "go to cart page"
        assert waitFor{at CartPage}

        when: "click button to go delivery page"
        to_purchase_procedure_btn.click()

        then: "location at delevery page now"
        assert waitFor{at DeliveryPage}
    }
    @Ignore
    def "Cart screen: delete one product in cart when have one product in cart" () {
        given: "go to index page"
        to IndexPage
        when: "choose one normal product"
        normal_product.click()

        then: "go to Produc Detail page"
        assert waitFor{at ProductDetailPage}

        when: "click button add to cart"
        add_to_cart_btn.click()

        then: "go to cart page"
        assert waitFor{at CartPage}

        when: "click delete product button"
        delete_product_btn.click()

        then: "display modal delete confirm"
        assert waitFor{modal_delete_confirm.displayed}

        when: "click cancel button in modal"
        modal_cancel_btn.click()

        then: "close modal delete confirm"
        assert waitFor{!modal_delete_confirm.displayed}

        when: "click button Cancel in confirm delete modal to close modal"
        delete_product_btn.click()

        then: "open delete confirm modal"
        assert waitFor{modal_delete_confirm.displayed}

        when: "click button Ok in confirm delete modal to delete product"
        modal_ok_btn.click()

        then: "delete display product form"
        assert !form1
    }

    @Ignore
    def "Cart screen: check recalculation" () {
        given: "go to index page"
        to IndexPage
        when: "choose one normal product"
        normal_product.click()

        then: "go to Produc Detail page"
        assert waitFor{at ProductDetailPage}

        when: "click button add to cart"
        add_to_cart_btn.click()

        then: "go to cart page"
        assert waitFor{at CartPage}

        when: "click button plus product quantity"
        //normal_quantity_416.value("2")
        plus_normal_quantity.click()

        then: "product quantity: 1 -> 2"
        waitFor{normal_quantity == "2"}

        when: "click button recalcutation price"
        recalculation_btn.click()

        then: "check result after recalculation"
/*		assert waitFor{price.text() == "216"}
		assert waitFor{cart_normal_total_price_top.text() == "216"}
		assert waitFor{cart_normal_total_price.text() == "216"}
		assert waitFor{cart_normal_final_ship_cost.text() == "0"}
		assert waitFor{cart_normal_final_total_cost.text() == "216"}*/
        assert waitFor{at CartPage}
    }
    @Ignore
    def "test"() {
        given: "go to index page"
        to IndexPage
        when: "choose one normal product"
        normal_product.click()

        then: "go to Produc Detail page"
        assert waitFor{at ProductDetailPage}

        when: "click button add to cart"
        add_to_cart_btn.click()

        then: "go to cart page"
        assert waitFor{at CartPage}
        a == b

        where:
        a         | b
        $("h4", 0).text() | "ショッピングカート"

    }

    def cleanupSpec() {
        browser.clearCookies()
    }
}

