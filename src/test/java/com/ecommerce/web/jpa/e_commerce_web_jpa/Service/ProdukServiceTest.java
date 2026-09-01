package com.ecommerce.web.jpa.e_commerce_web_jpa.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.web.jpa.e_commerce_web_jpa.model.produk.ProdukInsertRequest;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.produk.ProdukResponse;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.produk.ProdukUpdateRequest;
import com.ecommerce.web.jpa.e_commerce_web_jpa.service.produk.ProdukService;

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

        ProdukInsertRequest produk = new ProdukInsertRequest();
        produk.setId("BTK-123-K");
        produk.setNama("Kain Mega Mendung");
        produk.setStock(10);
        produk.setHarga(13.46);
        produk.setProductCategory("KAIN");
        produk.setGambar(mockMultipartFile);

        produkService.insert(produk);
    }

    @Test
    void testInsertFail() throws IOException {

        Path of = Path.of("batikhub-erd.png");
        byte[] allBytes = Files.readAllBytes(of);

        MultipartFile mockMultipartFile = new MockMultipartFile(
                "gambar", // nama field
                "batikhub-erd.png", // nama file asli
                "image/png", // tipe file
                allBytes); // byteFile

        ProdukInsertRequest produk = new ProdukInsertRequest();
        produk.setId("A01");
        produk.setNama("Kain Mega Mendung");
        produk.setStock(10);
        produk.setHarga(-13.46);
        produk.setProductCategory("KAIN");
        produk.setGambar(mockMultipartFile);

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            produkService.insert(produk);
        });
    }

    @Test
    void testFindById() {
        ProdukResponse byId = produkService.findById("BTK-345-K");

        Assertions.assertNotNull(byId);
        Assertions.assertEquals(byId.getHarga(), 13.46);
    }

    @Test
    void testFindByIdNotFound() {
        Assertions.assertThrows(ResponseStatusException.class, () -> {
            produkService.findById("kl543");
        });
    }

    @Test
    void testFindByIdBlank() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            produkService.findById("  ");
        });
    }

    @Test
    void testReduceStockSuccess() {
        produkService.kurangiStock(3, "BTK-345-K");

        Assertions.assertEquals(produkService.findById("BTK-345-K").getStock(), 7);
    }

    @Test
    void testReduceStockBlankIdAndMinusStock() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            produkService.kurangiStock(3, " "); // id blank
            produkService.kurangiStock(-3, "A01"); // minus stock
        });
    }

    @Test
    void testReduceStockIdNotFound() {
        produkService.kurangiStock(6, "kk-98");
    }

    @Test
    void testFindAllByNamaLikeFounded() {
        Page<ProdukResponse> byNama = produkService.findByNama("Kain");

        Assertions.assertEquals(byNama.getNumberOfElements(), 2);
        Assertions.assertEquals(byNama.getSize(), 6);
    }

    @Test
    void testFindAllByNamaLikeButNotFound() {
        Page<ProdukResponse> byNama = produkService.findByNama("Mouse");

        Assertions.assertEquals(byNama.getSize(), 6);
        Assertions.assertEquals(byNama.getNumberOfElements(), 0);
    }

    @Test
    void testFindAllByNamaLikeButBlank() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            produkService.findByNama("  ");
        });
    }

    @Test
    void testFIndAll() {
        Page<ProdukResponse> all = produkService.findAll();

        Assertions.assertEquals(all.getTotalElements(), 2);
        Assertions.assertEquals(all.getSize(), 6);
    }

    @Test
    void testUpdateSuccess() {
        ProdukUpdateRequest produk = new ProdukUpdateRequest();
        produk.setNama("Kemeja Batik Biru");
        produk.setProductCategory("");
        produk.setStock(5); // +5
        produk.setHarga(0.0);

        ProdukResponse update = produkService.update("BTK-345-K", produk);

        Assertions.assertEquals(update.getStock(), 12);
        Assertions.assertEquals(update.getNama(), "Kemeja Batik Biru");
        Assertions.assertEquals(update.getHarga(), 13.46);
    }

    @Test
    void testUpdateFail() {
        ProdukUpdateRequest produk = new ProdukUpdateRequest();
        produk.setNama("Kemeja Batik Biru");
        produk.setProductCategory("");
        produk.setStock(5);
        produk.setHarga(0.0);

        Assertions.assertThrows(ResponseStatusException.class, () -> {
            produkService.update("A2", produk);
        });
    }

    @Test
    void testDeleteSuccess() {
        produkService.delete("BTK-345-K");
    }

    @Test
    void testDeleteFail() {
        Assertions.assertThrows(ResponseStatusException.class, () -> {
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
