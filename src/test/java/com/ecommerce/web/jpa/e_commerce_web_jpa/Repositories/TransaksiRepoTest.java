package com.ecommerce.web.jpa.e_commerce_web_jpa.Repositories;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Member;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Produk;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Transaksi;

@SpringBootTest
public class TransaksiRepoTest {

    @Autowired
    private TransaksiRepository transaksiRepository;

    @Test
    void insert() {

        Member member = new Member();
        member.setId("M01");

        Produk produk = new Produk();
        produk.setId("A02");

        Transaksi transaksi = new Transaksi();
        transaksi.setIdMember(member);
        transaksi.setIdProduk(produk);
        transaksi.setTotalPembelian(3);
        transaksi.setPurchaseDate(LocalDate.now());
        transaksi.setArrivalDate(LocalDate.now()
                .plusDays(3)); // menambah tiga hari kedepan

        transaksiRepository.save(transaksi);
    }

    @Test
    void testFindById() {
        Transaksi transaksi = transaksiRepository.findById(1)
                .orElse(null);

        Assertions.assertNotNull(transaksi);
    }

    @Test
    void testFindAll() {
        List<Transaksi> all = transaksiRepository.findAll();

        Assertions.assertNotNull(all);

        for (Transaksi transaksi : all) {
            System.out.println(transaksi.getIdProduk().getNama() +
                    ", " + transaksi.getIdMember().getName() + ", " +
                    transaksi.getTotalPembelian());
        }
    }

    @Test
    void testRemove() {
        Transaksi transaksi = transaksiRepository.findById(1)
                .orElse(null);

        transaksi.getIdMember().setListTransaksi(null);
        transaksi.getIdProduk().setListTransaksi(null);
        transaksi.setIdMember(null);
        transaksi.setIdProduk(null);

        transaksiRepository.save(transaksi);

        transaksiRepository.delete(transaksi);
    }
}
