package com.ecommerce.web.jpa.e_commerce_web_jpa.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.web.jpa.e_commerce_web_jpa.Dto.Produk.ProdukInsertDTO;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Dto.Produk.ProdukUpdateDTO;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Produk;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Service.Produk.ProdukService;

import jakarta.validation.ConstraintViolationException;

@SpringBootTest
public class ProdukServiceTest {

    @Autowired
    private ProdukService produkService;

    @Test
    void testInsertSuccess() throws IOException {

        Path of = Path.of("batikhub-erd.png");
        byte[] allBytes = Files.readAllBytes(of);

        MultipartFile mockMultipartFile = new MockMultipartFile(
                "gambar", // nama field
                "batikhub-erd.png", // nama file asli
                "image/png", // tipe file
                allBytes); // byteFile

        ProdukInsertDTO produk = new ProdukInsertDTO();
        produk.setId("BTK-345-K");
        produk.setNama("Kain Mega Mendung");
        produk.setStock(10);
        produk.setHarga(13.46);
        produk.setProductCategory("KAIN");
        produk.setGambar(mockMultipartFile);

        produkService.insert(produk);
    }

    @Test
    void testInsertFail() {

        ProdukInsertDTO produk = new ProdukInsertDTO();
        produk.setId("BTK-345-K");
        produk.setNama("Kain Mega Mendung");
        produk.setStock(-10);
        produk.setHarga(13.46);
        produk.setProductCategory("KAIN");

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            produkService.insert(produk);
        });
    }

    @Test
    void testFindById() {
        Produk byId = produkService.findById("BTK-345-K");

        Assertions.assertNotNull(byId);
        Assertions.assertEquals(byId.getHarga(), 13.46);
    }

    @Test
    void testFindByIdNootFound() {
        Produk byId = produkService.findById("kl543");

        Assertions.assertNull(byId);
    }

    @Test
    void testFindByIdBlank() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            produkService.findById("  ");
        });
    }

    @Test
    void testReduceStock() {
        produkService.kurangiStock(3, "A01");

        Assertions.assertEquals(produkService.findById("A01").getStock(), 7);
    }

    @Test
    void testReduceStockBlankIdAndMinusStock() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            produkService.kurangiStock(3, " ");
            produkService.kurangiStock(-3, "A01");
        });
    }

    @Test
    void testReduceStockIdNotFound() {
        produkService.kurangiStock(6, "kk-98");
    }

    @Test
    void testFindAllByNamaLikeFounded() {
        List<Produk> byNama = produkService.findByNama("Kemeja");

        Assertions.assertEquals(byNama.size(), 1);
    }

    @Test
    void testFindAllByNamaLikeButNotFound() {
        List<Produk> byNama = produkService.findByNama(" Mouse");

        Assertions.assertEquals(byNama.size(), 0);
    }

    @Test
    void testFindAllByNamaLikeButBlank() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            produkService.findByNama("  ");
        });
    }

    @Test
    void testFIndAll() {
        List<Produk> all = produkService.findAll();
        Assertions.assertEquals(all.size(), 3);
    }

    @Test
    void testUpdateSuccess() {
        ProdukUpdateDTO produk = new ProdukUpdateDTO();
        produk.setNama("Kemeja Batik Biru");
        produk.setProductCategory("");
        produk.setStock(5);
        produk.setHarga(0.0);

        Produk update = produkService.update("A02", produk);

        Assertions.assertEquals(update.getStock(), 11);
        Assertions.assertEquals(update.getNama(), "Kemeja Batik Biru");
        Assertions.assertEquals(update.getHarga(), 26.25);
    }

    @Test
    void testUpdateFail() {
        ProdukUpdateDTO produk = new ProdukUpdateDTO();
        produk.setNama("Kemeja Batik Biru");
        produk.setProductCategory("");
        produk.setStock(5);
        produk.setHarga(0.0);

        Assertions.assertThrows(Exception.class, () -> {
            produkService.update("A2", produk);
        });
    }

    @Test
    void testDeleteSuccess() {
        produkService.delete("A01");
    }

    @Test
    void testDeleteFail() {
        Assertions.assertThrows(Exception.class, () -> {
            produkService.delete("A01");
        });
    }

    @Test
    void testDeleteBlankId() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            produkService.delete("  ");
        });
    }
}
