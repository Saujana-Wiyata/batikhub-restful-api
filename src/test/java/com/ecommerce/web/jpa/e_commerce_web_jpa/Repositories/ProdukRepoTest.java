package com.ecommerce.web.jpa.e_commerce_web_jpa.Repositories;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Produk;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Enum.ProductCategory;

@SpringBootTest
public class ProdukRepoTest {

    @Autowired
    private ProdukRepository produkRepository;

    @Test
    void insert() throws IOException {

        Path of = Path.of("batikhub-erd.png");
        byte[] gambarByte = Files.readAllBytes(of);

        Produk produk = new Produk();
        produk.setId("A02");
        produk.setNama("Kemeja");
        produk.setHarga(54);
        produk.setStock(8);
        produk.setGambar(gambarByte);

        produkRepository.save(produk);
    }

    @Test
    void update() {

        Produk produk = produkRepository.findById("A02").orElse(null);
        produk.setProductCategory(ProductCategory.BAJU);
        produkRepository.save(produk);

        Assertions.assertEquals(ProductCategory.BAJU, produkRepository.findById("A02")
                .orElse(null).getProductCategory());

    }

    @Test
    void delete() {
        Produk produk = produkRepository.findById("A01")
                .orElse(null);

        produkRepository.delete(produk);
    }

    @Test
    void testReduceStock() {
        produkRepository.reduceStock(2, "A02");

        Assertions.assertEquals(6, produkRepository.findById("A02")
                .orElse(null).getStock());
    }

    @Test
    void testFindNamaProduk() {
        List<Produk> result = produkRepository.findAllByNamaLike("Kemeja");

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void testFindNamaProdukEmpty() {
        List<Produk> result = produkRepository.findAllByNamaLike("Sepatu");

        Assertions.assertEquals(0, result.size());
    }

    @Test
    void testFindAll() {
        List<Produk> all = produkRepository.findAll();

        Assertions.assertNotNull(all);
        Assertions.assertEquals(1, all.size());
    }
}
