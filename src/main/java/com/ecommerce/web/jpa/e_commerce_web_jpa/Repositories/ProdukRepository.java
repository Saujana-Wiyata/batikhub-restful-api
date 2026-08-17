package com.ecommerce.web.jpa.e_commerce_web_jpa.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Produk;

import jakarta.transaction.Transactional;

public interface ProdukRepository extends JpaRepository<Produk, String> {

    @Modifying
    @Transactional
    @Query("UPDATE Produk p SET p.stock = p.stock - :stockPembelian WHERE ID = :id")
    void reduceStock(int stockPembelian, @Param("id") String idProduk);

    @Transactional
    List<Produk> findAllByNamaLike(String nama);

}
