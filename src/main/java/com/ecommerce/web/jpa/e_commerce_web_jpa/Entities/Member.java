package com.ecommerce.web.jpa.e_commerce_web_jpa.Entities;

import java.util.List;

import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Embed.Alamat;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "member")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    private String id;

    private String name;

    private String email;

    private String password;

    @Embedded
    private Alamat alamat;

    @OneToMany(mappedBy = "idMember")
    // @Column(name = "transaksi")
    private List<Transaksi> listTransaksi;

}
