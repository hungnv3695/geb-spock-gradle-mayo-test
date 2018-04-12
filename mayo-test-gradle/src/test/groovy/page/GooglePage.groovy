package page

import geb.Page

class GooglePage extends Page  {
    static url = "https://www.google.com.vn/"
    static at = {title == "Google"}
    static content  = {
        textbox {$("input#lst-ib")}
    }

}
