package com.ecommerce.web.jpa.e_commerce_web_jpa.Service.Transaksi;

import com.ecommerce.web.jpa.e_commerce_web_jpa.Service.Omset.OmsetService;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.ecommerce.web.jpa.e_commerce_web_jpa.Dto.Transaksi.TransaksiInsertDTO;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Member;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Produk;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Transaksi;

import com.ecommerce.web.jpa.e_commerce_web_jpa.Repositories.TransaksiRepository;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Service.Produk.ProdukService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@Service
@Validated
@RequiredArgsConstructor
public class TransaksiServiceImpl implements TransaksiService {

    private final TransaksiRepository transaksiRepository;

    private final ProdukService produkService;

    private final OmsetService omsetService;

    @Override
    public void insert(@Valid TransaksiInsertDTO transaksi) {

        Member member = new Member();
        member.setId(transaksi.getIdMember());

        Produk produk = new Produk();
        produk.setId(transaksi.getIdProduk());

        Transaksi transaksiEntity = new Transaksi();
        transaksiEntity.setTotalPembelian(transaksi.getTotalPembelian());
        transaksiEntity.setPurchaseDate(transaksi.getPurchaseDate());
        transaksiEntity.setArrivalDate(transaksi.getArrivalDate());
        transaksiEntity.setIdMember(member);
        transaksiEntity.setIdProduk(produk);

        transaksiRepository.save(transaksiEntity);
    }

    private void reduceStack(Transaksi transaksi) {
        produkService.kurangiStock(transaksi.getTotalPembelian(), transaksi
                .getIdProduk().getId());
    }

    private void addJumlahPenjualan(Transaksi transaksi) {
        omsetService.tambahJumlahPenjualan(transaksi.getTotalPembelian(), transaksi
                .getIdProduk().getId());
    }

    private void breakRelation(Transaksi transaksi) {
        transaksi.getIdMember().setListTransaksi(null);
        transaksi.getIdProduk().setListTransaksi(null);

        transaksi.setIdMember(null);
        transaksi.setIdProduk(null);
    }

    @Override
    public void delete(@Positive int idTransaksi) {
        Transaksi transaksi = transaksiRepository.findById(idTransaksi)
                .orElse(null);

        reduceStack(transaksi);
        addJumlahPenjualan(transaksi);

        breakRelation(transaksi);

        transaksiRepository.delete(transaksi);
    }

    @Override
    public List<Transaksi> findAll() {
        return transaksiRepository.findAll();
    }

    @Override
    public Transaksi findById(@Positive int idTransaksi) {
        return transaksiRepository.findById(idTransaksi).orElse(null);
    }

}