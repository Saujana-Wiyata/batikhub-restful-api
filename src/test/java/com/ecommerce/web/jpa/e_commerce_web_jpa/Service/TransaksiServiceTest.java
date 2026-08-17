package com.ecommerce.web.jpa.e_commerce_web_jpa.Service;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ecommerce.web.jpa.e_commerce_web_jpa.Dto.Transaksi.TransaksiInsertDTO;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Transaksi;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Service.Transaksi.TransaksiService;

import jakarta.validation.ConstraintViolationException;

@SpringBootTest
public class TransaksiServiceTest {

    @Autowired
    private TransaksiService transaksiService;

    @Test
    void testInsertSuccess() {
        TransaksiInsertDTO transaksiInsertDTO = new TransaksiInsertDTO();
        transaksiInsertDTO.setTotalPembelian(1);
        transaksiInsertDTO.setPurchaseDate(LocalDate.now());
        transaksiInsertDTO.setArrivalDate(LocalDate.now().plusDays(3));
        transaksiInsertDTO.setIdProduk("A02");
        transaksiInsertDTO.setIdMember("80a542");

        transaksiService.insert(transaksiInsertDTO);
    }

    @Test
    void testInsertFail() {
        TransaksiInsertDTO transaksiInsertDTO = new TransaksiInsertDTO();
        transaksiInsertDTO.setTotalPembelian(1);
        transaksiInsertDTO.setPurchaseDate(LocalDate.now());
        transaksiInsertDTO.setArrivalDate(LocalDate.now().minusDays(3));
        transaksiInsertDTO.setIdProduk("BTK-345-K");
        transaksiInsertDTO.setIdMember("a757eb");

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            transaksiService.insert(transaksiInsertDTO);
        });
    }

    @Test
    void testInsertFKNotFound() {
        TransaksiInsertDTO transaksiInsertDTO = new TransaksiInsertDTO();
        transaksiInsertDTO.setTotalPembelian(1);
        transaksiInsertDTO.setPurchaseDate(LocalDate.now());
        transaksiInsertDTO.setArrivalDate(LocalDate.now().minusDays(3));
        transaksiInsertDTO.setIdProduk("BTK-345-K");
        transaksiInsertDTO.setIdMember("AHHAHA");

        Assertions.assertThrows(Exception.class, () -> {
            transaksiService.insert(transaksiInsertDTO);
        });
    }

    @Test
    void testDeleteSuccess() {
        transaksiService.delete(6);
    }

    @Test
    void testDeleteFail() {
        Assertions.assertThrows(Exception.class, () -> {
            transaksiService.delete(2);
        });
    }

    @Test
    void testFindAll() {
        List<Transaksi> all = transaksiService.findAll();
        Assertions.assertEquals(all.size(), 2);
    }

    @Test
    void testFindByIdSuccess() {
        Transaksi byId = transaksiService.findById(5);

        Assertions.assertNotNull(byId);
        Assertions.assertEquals(byId.getIdMember().getName(), "Aidil Syahmi");
    }

    @Test
    void testFindByIdFail() {
        Transaksi byId = transaksiService.findById(2);
        Assertions.assertNull(byId);
    }

    @Test
    void testFindByIdMinus() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            transaksiService.findById(-7);
        });
    }
}
