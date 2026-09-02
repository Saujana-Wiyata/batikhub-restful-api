package com.ecommerce.web.jpa.e_commerce_web_jpa.service.transaksi;

import org.springframework.data.domain.Page;

import com.ecommerce.web.jpa.e_commerce_web_jpa.model.transaksi.TransaksiInsertRequest;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.transaksi.TransaksiResponse;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

public interface TransaksiService {

    void insert(@Valid TransaksiInsertRequest transaksi);

    void delete(@Positive int idTransaksi);

    Page<TransaksiResponse> findAll();

}
