package com.ecommerce.web.jpa.e_commerce_web_jpa.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.web.jpa.e_commerce_web_jpa.model.transaksi.TransaksiInsertRequest;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.transaksi.TransaksiResponse;
import com.ecommerce.web.jpa.e_commerce_web_jpa.service.transaksi.TransaksiService;

import jakarta.validation.ConstraintViolationException;

@SpringBootTest
public class TransaksiServiceTest {

    @Autowired
    private TransaksiService transaksiService;

    @Test
    void testInsertSuccess() {
        TransaksiInsertRequest transaksiInsertDTO = new TransaksiInsertRequest();
        transaksiInsertDTO.setTotalPembelian(3);
        transaksiInsertDTO.setIdProduk("BTK-123-K");
        transaksiInsertDTO.setTokenMember("48e83a");

        transaksiService.insert(transaksiInsertDTO);
    }

    @Test
    void testInsertFail() {
        TransaksiInsertRequest transaksiInsertDTO = new TransaksiInsertRequest();
        transaksiInsertDTO.setTotalPembelian(-1);
        transaksiInsertDTO.setIdProduk("");
        transaksiInsertDTO.setTokenMember(" ");

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            transaksiService.insert(transaksiInsertDTO);
        });
    }

    @Test
    void testInsertFKNotFound() {
        TransaksiInsertRequest transaksiInsertDTO = new TransaksiInsertRequest();
        transaksiInsertDTO.setTotalPembelian(1);
        transaksiInsertDTO.setIdProduk("BTK-123-K");
        transaksiInsertDTO.setTokenMember("AHHAHA");

        Assertions.assertThrows(ResponseStatusException.class, () -> {
            transaksiService.insert(transaksiInsertDTO);
        });
    }

    @Test
    void testFindAll() {
        Page<TransaksiResponse> all = transaksiService.findAll();

        Assertions.assertEquals(1, all.getTotalElements());
        Assertions.assertEquals(5, all.getSize());
    }

    @Test
    void testDeleteSuccess() {
        transaksiService.delete(7);
    }

    @Test
    void testDeleteFail() {
        Assertions.assertThrows(ResponseStatusException.class, () -> {
            transaksiService.delete(2);
        });
    }

}
