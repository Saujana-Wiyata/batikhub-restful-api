package com.ecommerce.web.jpa.e_commerce_web_jpa.Controller.Member;

import java.time.LocalDate;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.ecommerce.web.jpa.e_commerce_web_jpa.Dto.Transaksi.TransaksiInsertDTO;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Member;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Produk;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Embed.Alamat;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Service.Member.MemberService;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Service.Produk.ProdukService;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Service.Transaksi.TransaksiService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequiredArgsConstructor
public class BuyProductController {

    // memerlukan data idProduk
    private String idProduk;

    private final ProdukService produkService;

    private final MemberService memberService;

    private final TransaksiService transaksiService;

    private String getIdProduk() {
        return idProduk;
    }

    private void setIdProduk(String idProduk) {
        this.idProduk = idProduk;
    }

    private Map<String, Object> unpackedProdukDetail(
            Produk produkId,
            String idCookie) {
        Map<String, Object> productDetail = new HashMap<>();
        productDetail.put("id", produkId.getId());
        productDetail.put("nama", produkId.getNama());
        productDetail.put("harga", produkId.getHarga());
        productDetail.put("stock", produkId.getStock());
        productDetail.put("category", produkId.getProductCategory());

        if (produkId.getGambar() != null && produkId.getGambar().length > 0) {
            String gambarAsli = Base64.getEncoder().encodeToString(produkId.getGambar());
            productDetail.put("gambar", gambarAsli);
        } else {
            productDetail.put("gambar", null);
        }

        // setting data calon pembeli
        Member memberId = memberService.findById(idCookie);
        productDetail.put("memberName", memberId.getName());

        Alamat alamatMember = memberId.getAlamat();
        productDetail.put("memberAlamat",
                alamatMember.getJalan() + ", " + alamatMember.getKota() + ", "
                        + alamatMember.getProvinsi());

        return productDetail;
    }

    @PostMapping("/buyproduct")
    @ResponseStatus(value = HttpStatus.OK)
    public ModelAndView getPageBuyProduct(
            @RequestParam String idProduk,
            @CookieValue(value = "id") String valueCookie) {

        Produk produkId = produkService.findById(idProduk);

        Map<String, Object> unpackedProdukDetail = unpackedProdukDetail(produkId, valueCookie);

        setIdProduk(idProduk); // masukin idproduk

        return new ModelAndView("visitor/buyPage", Map.of(
                "produkId", unpackedProdukDetail));
    }

    @PostMapping("/proccesspurcaheseproduct")
    public ModelAndView postProccessBuyProduct(
            @RequestParam String totalBelanja,
            @CookieValue(value = "id") String idMember) {

        TransaksiInsertDTO transaksiInsertDTO = new TransaksiInsertDTO();
        transaksiInsertDTO.setTotalPembelian(Integer.parseInt(totalBelanja));
        transaksiInsertDTO.setPurchaseDate(LocalDate.now());
        transaksiInsertDTO.setArrivalDate(LocalDate.now().plusDays(7));
        transaksiInsertDTO.setIdMember(idMember);
        transaksiInsertDTO.setIdProduk(getIdProduk());

        transaksiService.insert(transaksiInsertDTO);

        return new ModelAndView("redirect:/pengiriman");
    }

}
