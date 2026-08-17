package com.ecommerce.web.jpa.e_commerce_web_jpa.Service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ecommerce.web.jpa.e_commerce_web_jpa.Dto.Omset.OmsetDTO;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Dto.Produk.ProdukIdDTO;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Omset;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Service.Omset.OmsetService;

import jakarta.validation.ConstraintViolationException;

@SpringBootTest
public class OmsetServiceTest {

    @Autowired
    private OmsetService omsetService;

    @Test
    void testInsertSuccess() {

        ProdukIdDTO produk = new ProdukIdDTO();
        produk.setId("A02");

        OmsetDTO omset = new OmsetDTO();
        omset.setIdProduk(produk);
        omset.setJumlahPenjualan(5);

        omsetService.insert(omset);
    }

    @Test
    void testInsertFail() {
        ProdukIdDTO produk = new ProdukIdDTO();
        produk.setId("  ");

        OmsetDTO omset = new OmsetDTO();
        omset.setIdProduk(produk);
        omset.setJumlahPenjualan(4);

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            omsetService.insert(omset);
        });

    }

    @Test
    void testDeleteSuccess() {
        omsetService.delete(8);
    }

    @Test
    void testDeleteFail() {
        Assertions.assertThrows(Exception.class, () -> {
            omsetService.delete(1);
        });
    }

    @Test
    void testDeleteMinusId() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            omsetService.delete(0);
        });
    }

    @Test
    void testTambahJumlahPenjualanSuccess() {
        omsetService.tambahJumlahPenjualan(2, "BTK-345-K");
    }

    @Test
    void testTambahJumlahPenjualanCOnstraintViolation() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            omsetService.tambahJumlahPenjualan(2, " ");
            omsetService.tambahJumlahPenjualan(-2, "BTK-345-K");
        });
    }

    @Test
    void testJumlahHargaPerProduk() {
        Double jumlahHargaPerProduk = omsetService
                .jumlahHargaPerProduk("BTK-345-K");
        Assertions.assertEquals((13 * 10), jumlahHargaPerProduk);
    }

    @Test
    void testJumlahHargaPerProdukBlankId() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            omsetService.jumlahHargaPerProduk(" ");
        });
    }

    @Test
    void testTotalOmset() {
        Assertions.assertEquals((10 * 13) + (5 * 26.25), omsetService.totalKeseluruhanOmset());
    }

    @Test
    void testJumlahProdukTerjual() {
        Assertions.assertEquals((10 + 5), omsetService.totalProdukTerjual());
    }

    @Test
    void testFindAll() {
        List<Omset> all = omsetService.findAll();

        Assertions.assertNotNull(all);
        Assertions.assertEquals(2, all.size());
    }
}
