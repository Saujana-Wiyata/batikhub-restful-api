package com.ecommerce.web.jpa.e_commerce_web_jpa.Controller.Member;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class PromoPageController {

    @GetMapping("/promo")
    @ResponseStatus(value = HttpStatus.OK)
    public ModelAndView getPage() {
        return new ModelAndView("visitor/promoPage");
    }
}