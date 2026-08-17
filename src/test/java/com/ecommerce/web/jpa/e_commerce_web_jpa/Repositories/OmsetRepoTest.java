package com.ecommerce.web.jpa.e_commerce_web_jpa.Repositories;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Omset;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Produk;

@SpringBootTest
public class OmsetRepoTest {

    @Autowired
    private OmsetRespository omsetRespository;

    @Test
    void insert() {

        Produk produk = new Produk();
        produk.setId("A02");

        Omset omset = new Omset();
        omset.setIdProduk(produk);
        omset.setJumlahPenjualan(6);

        omsetRespository.save(omset);

    }

    @Test
    void testTambahJumlahPenjualan() {
        omsetRespository.tambahJumlahPenjualan(3, "A01");

        Assertions.assertEquals(5, omsetRespository.findById(3).orElse(null)
                .getJumlahPenjualan());
    }

    @Test
    void testGetOmsetPerProduct() {
        Double omset = omsetRespository.jumlahHargaPerProduk("A02");

        Assertions.assertEquals((6 * 54), omset);
        System.out.println(omset);
    }

    @Test
    void testGetAllOmset() {
        Double jumlahOmset = omsetRespository.jumlahOmset();

        Assertions.assertEquals((6 * 54) + (5 * 20.7), jumlahOmset);
        System.out.println(jumlahOmset);
    }

    @Test
    void testShowAll() {
        List<Omset> all = omsetRespository.findAll();

        Assertions.assertNotNull(all);

        for (Omset omset : all) {
            System.out.println(omset.getJumlahPenjualan() + ", " + omset.getIdProduk().getNama());
        }
    }

    @Test
    void testDelete() {
        Omset omset = omsetRespository.findById(3)
                .orElse(null);

        omset.getIdProduk().setOmset(null);
        omset.setIdProduk(null);

        omsetRespository.save(omset);

        omsetRespository.delete(omset);
    }

    @Test
    void testGetJumlahProdukTerjual() {
        Integer jumlahProdukTerjual = omsetRespository.jumlahProdukTerjual();

        Assertions.assertEquals(6, jumlahProdukTerjual);
    }
}
