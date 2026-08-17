package com.ecommerce.web.jpa.e_commerce_web_jpa.Controller.Staff;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Embed.Alamat;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Service.Transaksi.TransaksiService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DeliveryPageController {

    private final TransaksiService transaksiService;

    private List<Map<String, Object>> unpackedDetail() {

        List<Map<String, Object>> list = transaksiService.findAll().stream()
                .map(transaksi -> {

                    Map<String, Object> tempData = new HashMap<>();

                    Alamat alamat = transaksi.getIdMember().getAlamat();

                    String gambarEncode = Base64.getEncoder()
                            .encodeToString(transaksi.getIdProduk().getGambar());

                    tempData.put("gambarProduk", gambarEncode);
                    tempData.put("idProduk", transaksi.getIdProduk().getId());
                    tempData.put("namaProduk", transaksi.getIdProduk().getNama());
                    tempData.put("idTransaksi", transaksi.getId());
                    tempData.put("totalDibeli", transaksi.getTotalPembelian());
                    tempData.put("hargaProduk", transaksi.getIdProduk().getHarga());
                    tempData.put("namaPembeli", transaksi.getIdMember().getName());
                    tempData.put("alamatPembeli", alamat.getJalan() + ", " + alamat.getKota()
                            + "," + alamat.getProvinsi());

                    return tempData;
                }).toList();

        return list;
    }

    @GetMapping(path = "/delivery")
    public ModelAndView getPageDelivery() {
        return new ModelAndView("staff/deliveryPage", Map.of(
                "transaksi", unpackedDetail(),
                "totalPaket", transaksiService.findAll().size()));
    }

}
