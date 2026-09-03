package com.ecommerce.web.jpa.e_commerce_web_jpa.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.ecommerce.web.jpa.e_commerce_web_jpa.model.omset.OmsetRequest;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.omset.OmsetResponse;
import com.ecommerce.web.jpa.e_commerce_web_jpa.service.omset.OmsetService;

import jakarta.validation.ConstraintViolationException;

@SpringBootTest
public class OmsetServiceTest {

    @Autowired
    private OmsetService omsetService;

    @Test
    void testInsertSuccess() {

        OmsetRequest omset = new OmsetRequest();
        omset.setIdProduk("BTK-321-K");
        omset.setJumlahPenjualan(5);

        omsetService.insert(omset);
    }

    @Test
    void testInsertFail() {

        OmsetRequest omset = new OmsetRequest();
        omset.setIdProduk("");
        omset.setJumlahPenjualan(4);

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            omsetService.insert(omset);
        });

    }

    @Test
    void testTambahJumlahPenjualanSuccess() {
        omsetService.tambahJumlahPenjualan(2, "BTK-123-K");
    }

    @Test
    void testTambahJumlahPenjualanFail() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            omsetService.tambahJumlahPenjualan(2, " ");
            omsetService.tambahJumlahPenjualan(-2, "BTK-345-K");
        });
    }

    @Test
    void testFindAll() {
        Page<OmsetResponse> all = omsetService.findAll();

        Assertions.assertEquals(2, all.getTotalElements());
        Assertions.assertEquals(2, all.getContent().size());

    }
}
