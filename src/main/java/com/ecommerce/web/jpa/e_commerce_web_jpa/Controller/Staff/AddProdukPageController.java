package com.ecommerce.web.jpa.e_commerce_web_jpa.Controller.Staff;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.ecommerce.web.jpa.e_commerce_web_jpa.Dto.Produk.ProdukInsertDTO;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Service.Produk.ProdukService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AddProdukPageController {

    private final ProdukService produkService;

    @GetMapping(path = "/addproduk")
    @ResponseStatus(value = HttpStatus.OK)
    public ModelAndView getPageAddProduk() {
        return new ModelAndView("staff/addProdukPage");
    }

    @PostMapping(path = "/insertproduk")
    public ModelAndView postDataProduk(@ModelAttribute ProdukInsertDTO produk) {
        produkService.insert(produk);

        return new ModelAndView("redirect:/listproduk");
    }

}
