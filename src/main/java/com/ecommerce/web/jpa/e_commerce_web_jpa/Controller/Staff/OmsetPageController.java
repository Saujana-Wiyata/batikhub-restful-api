package com.ecommerce.web.jpa.e_commerce_web_jpa.Controller.Staff;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ecommerce.web.jpa.e_commerce_web_jpa.Service.Omset.OmsetService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class OmsetPageController {

    private final OmsetService omsetService;

    private List<Map<String, Object>> unpacked() {

        List<Map<String, Object>> list = omsetService.findAll().stream()
                .map(omset -> {

                    String produkId = omset.getIdProduk().getId();
                    Map<String, Object> temporary = new HashMap<>();

                    temporary.put("idProduk", produkId);
                    temporary.put("namaProduk", omset.getIdProduk().getNama());
                    temporary.put("omsetPerProduk", omsetService.jumlahHargaPerProduk(produkId));
                    temporary.put("stockTerjualPerProduk", omset.getJumlahPenjualan());

                    return temporary;
                }).toList();

        return list;
    }

    @GetMapping(path = "/dashboard")
    public ModelAndView getPage() {

        return new ModelAndView("staff/omsetPage", Map.of(
                "totalProdukTerjual", omsetService.totalProdukTerjual(),
                "totalOmset", omsetService.totalKeseluruhanOmset(),
                "produks", unpacked()));
    }

}
