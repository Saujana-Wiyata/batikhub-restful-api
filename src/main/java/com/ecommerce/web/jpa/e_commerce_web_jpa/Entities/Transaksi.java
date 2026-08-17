package com.ecommerce.web.jpa.e_commerce_web_jpa.Entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "transaksi")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaksi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "total_pembelian")
    private Integer totalPembelian;

    @Column(name = "purchase_date")
    private LocalDate purchaseDate;

    @Column(name = "arrival_date")
    private LocalDate arrivalDate;

    @ManyToOne
    @JoinColumn(name = "id_member", referencedColumnName = "id")
    private Member idMember;

    @ManyToOne
    @JoinColumn(name = "id_produk", referencedColumnName = "id")
    private Produk idProduk;

}
