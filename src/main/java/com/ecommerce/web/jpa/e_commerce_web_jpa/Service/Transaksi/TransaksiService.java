package com.ecommerce.web.jpa.e_commerce_web_jpa.Service.Transaksi;

import java.util.List;

import com.ecommerce.web.jpa.e_commerce_web_jpa.Dto.Transaksi.TransaksiInsertDTO;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Transaksi;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

public interface TransaksiService {

    void insert(@Valid TransaksiInsertDTO transaksi);

    void delete(@Positive int idTransaksi);

    List<Transaksi> findAll();

    Transaksi findById(@Positive int idTransaksi);

}
