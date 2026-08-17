package com.ecommerce.web.jpa.e_commerce_web_jpa.Controller.Staff;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ecommerce.web.jpa.e_commerce_web_jpa.Dto.Produk.ProdukUpdateDTO;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Produk;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Service.Produk.ProdukService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EditProductPageController {

    private final ProdukService produkService;

    private String idProduk;

    private String getIdProduk() {
        return idProduk;
    }

    private void setIdProduk(String idProduk) {
        this.idProduk = idProduk;
    }

    private Map<String, Object> unpackedDetailProduct(String idProduk) {

        Produk productById = produkService.findById(idProduk);

        Map<String, Object> tempData = new HashMap<>();
        tempData.put("id", productById.getId());
        tempData.put("nama", productById.getNama());
        tempData.put("stock", productById.getStock());
        tempData.put("harga", productById.getHarga());
        tempData.put("productCategory", productById.getProductCategory());

        if (productById.getGambar() != null && productById.getGambar().length > 0) {
            tempData.put("gambar", Base64.getEncoder()
                    .encodeToString(productById.getGambar()));

        } else {
            tempData.put("gambar", null);
        }
        return tempData;
    }

    @PostMapping(path = "/editproduct")
    public ModelAndView getPageEditProduct(@RequestParam String idProduk) {
        setIdProduk(idProduk);
        return new ModelAndView("staff/editProductPage", Map.of(
                "produk", unpackedDetailProduct(idProduk)));
    }

    @PostMapping(path = "/updateproduk")
    public ModelAndView postMethodName(@ModelAttribute ProdukUpdateDTO produk) {

        produkService.update(getIdProduk(), produk);

        return new ModelAndView("redirect:/listproduk");
    }

}
