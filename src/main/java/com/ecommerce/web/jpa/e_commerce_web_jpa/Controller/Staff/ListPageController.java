package com.ecommerce.web.jpa.e_commerce_web_jpa.Controller.Staff;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.ecommerce.web.jpa.e_commerce_web_jpa.Service.Produk.ProdukService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ListPageController {

    private final ProdukService produkService;

    private List<Map<String, Object>> unpackedDetailProduk() {

        List<Map<String, Object>> list = produkService.findAll().stream()
                .map(produks -> {
                    Map<String, Object> temporaryDetail = new HashMap<>();

                    temporaryDetail.put("idProduk", produks.getId());
                    temporaryDetail.put("namaProduk", produks.getNama());
                    temporaryDetail.put("stock", produks.getStock());
                    temporaryDetail.put("harga", produks.getHarga());

                    if (produks.getGambar() != null && produks.getGambar().length > 0) {

                        temporaryDetail.put("gambar", Base64.getEncoder()
                                .encodeToString(produks.getGambar()));
                    } else {
                        temporaryDetail.put("gambar", null);
                    }

                    return temporaryDetail;
                }).toList();

        return list;
    }

    @GetMapping(path = "/listproduk")
    @ResponseStatus(value = HttpStatus.OK)
    public ModelAndView getMethodName() {
        return new ModelAndView("staff/listPage", Map.of(
                "produk", unpackedDetailProduk()));
    }

    @PostMapping(path = "/deleteproduct")
    public ModelAndView postMethodName(@RequestParam String idProduk) {
        produkService.delete(idProduk);

        return new ModelAndView("redirect:/listproduk");
    }

}
