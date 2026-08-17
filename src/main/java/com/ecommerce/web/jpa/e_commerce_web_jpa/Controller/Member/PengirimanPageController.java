package com.ecommerce.web.jpa.e_commerce_web_jpa.Controller.Member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import com.ecommerce.web.jpa.e_commerce_web_jpa.Service.Transaksi.TransaksiService;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Utilities.UtilityCookieName;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class PengirimanPageController {

    private final TransaksiService transaksiService;

    private List<Map<String, Object>> unpackedPengirimanDetail() {
        String idCookie = UtilityCookieName.getId();

        // dikaarenakan List, kita pakai stream. tambahan pula ada situasi yg perlu
        // dipenuhi
        List<Map<String, Object>> list = transaksiService.findAll()
                .stream().filter(t -> t.getIdMember().getId().equals(idCookie))
                .map(transaksi -> {
                    Map<String, Object> deliveryDetail = new HashMap<>();
                    deliveryDetail.put("idPengiriman", transaksi.getId());
                    deliveryDetail.put("idProduk", transaksi.getIdProduk().getId());
                    deliveryDetail.put("purchaseDate", transaksi.getPurchaseDate());
                    deliveryDetail.put("arrivalDate", transaksi.getArrivalDate());
                    deliveryDetail.put("hargaPerProduk", transaksi.getIdProduk().getHarga());
                    deliveryDetail.put("jmlhPembelian", transaksi.getTotalPembelian());
                    deliveryDetail.put("produkName", transaksi.getIdProduk().getNama());
                    deliveryDetail.put("totalharga", (transaksi.getIdProduk().getHarga() *
                            transaksi.getTotalPembelian()));

                    return deliveryDetail;
                }).toList();

        return list;
    }

    @GetMapping(path = "/pengiriman")
    @ResponseStatus(value = HttpStatus.OK)
    public ModelAndView getPagePengiriman(@CookieValue(value = "id") String idCookie) {
        UtilityCookieName.setId(idCookie);

        return new ModelAndView("visitor/pengirimanPage", Map.of(
                "delivery", unpackedPengirimanDetail()));
    }

    @PostMapping(path = "/deletetransaksi")
    public ModelAndView postProccessDeleteTransaksi(@RequestParam String idTransaksi) {
        transaksiService.delete(Integer.parseInt(idTransaksi));

        return new ModelAndView("redirect:/findproduct");
    }

}
