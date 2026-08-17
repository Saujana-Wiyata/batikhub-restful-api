package com.ecommerce.web.jpa.e_commerce_web_jpa.Controller.Member;

import java.util.Base64;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Produk;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Service.Produk.ProdukService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AllProdukController {

    private final ProdukService produkService;

    private List<Map<String, Object>> unpackProdukList(List<Produk> produks) {
        // bongkar satu persatu melalui stream(), dan ubah ke bentuk map supaya
        // detailnya bisa dibongkar
        List<Map<String, Object>> listProduk = produks.stream()
                .map(produk -> {

                    // tempat tampung sementara
                    Map<String, Object> produkDetail = new Hashtable<>();
                    produkDetail.put("id", produk.getId());
                    produkDetail.put("nama", produk.getNama());
                    produkDetail.put("harga", produk.getHarga());
                    produkDetail.put("stock", produk.getStock());
                    produkDetail.put("productCategory", produk.getProductCategory());

                    if (produk.getGambar() != null && produk.getGambar().length > 0) {
                        // convert gambar
                        String gambar = Base64.getEncoder().encodeToString(produk.getGambar());
                        produkDetail.put("gambar", gambar);
                    } else {
                        produkDetail.put("gambar", null);
                    }

                    return produkDetail;
                }).toList(); // convert lagi ke list

        return listProduk;
    }

    @GetMapping(path = "/findproduct")
    @ResponseStatus(value = HttpStatus.OK)
    public ModelAndView getPageShowAllProduk() {
        /*
         * dikarenakan kita harus ubah gambar dari byte[] ke bentuk aslinya,
         * kita harus bongkar isi detail produk satu persatu lagi
         */

        List<Map<String, Object>> unpackProdukList = unpackProdukList(produkService.findAll());

        return new ModelAndView("visitor/cariPage", Map.of(
                "produk", unpackProdukList,
                "totalProduk", unpackProdukList.size()));
    }

    @PostMapping(path = "/findbyname")
    @ResponseStatus(value = HttpStatus.OK)
    public ModelAndView postFindProduk(HttpServletRequest request) {
        String paramProdukName = request.getParameter("produkName");

        List<Map<String, Object>> unpackProdukList = unpackProdukList(produkService
                .findByNama(paramProdukName));

        return new ModelAndView("visitor/cariPage", Map.of(
                "produk", unpackProdukList,
                "totalProduk", unpackProdukList.size()));

    }

}
