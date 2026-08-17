package com.ecommerce.web.jpa.e_commerce_web_jpa.Entities;

import java.util.List;

import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Enum.ProductCategory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "produk")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produk {

    @Id
    private String id;

    private String nama;

    private Integer stock;

    private double harga;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;

    @Lob
    private byte[] gambar;

    @OneToMany(mappedBy = "idProduk")
    // @Column(name = "transaksi")
    private List<Transaksi> listTransaksi;

    @OneToOne(mappedBy = "idProduk")
    private Omset omset;
}
