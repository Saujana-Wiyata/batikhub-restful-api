package com.ecommerce.web.jpa.e_commerce_web_jpa.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Omset;

public interface OmsetRespository extends JpaRepository<Omset, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Omset o SET o.jumlahPenjualan = o.jumlahPenjualan + :pembelian WHERE o.idProduk.id = :idProduk")
    void tambahJumlahPenjualan(@Param("pembelian") int jumlahPembelian, String idProduk);

    @Query("SELECT (o.jumlahPenjualan * p.harga) FROM Omset o JOIN o.idProduk p WHERE p.id = :idProduk")
    Double jumlahHargaPerProduk(String idProduk);

    @Query("SELECT SUM(o.jumlahPenjualan * p.harga) FROM Omset o JOIN o.idProduk p")
    Double jumlahOmset();

    @Query("SELECT SUM(o.jumlahPenjualan) FROM Omset o")
    Integer jumlahProdukTerjual();
}
