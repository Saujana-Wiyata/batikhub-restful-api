package com.ecommerce.web.jpa.e_commerce_web_jpa.service.transaksi;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.web.jpa.e_commerce_web_jpa.entities.Member;
import com.ecommerce.web.jpa.e_commerce_web_jpa.entities.Produk;
import com.ecommerce.web.jpa.e_commerce_web_jpa.entities.Transaksi;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.transaksi.TransaksiInsertRequest;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.transaksi.TransaksiResponse;
import com.ecommerce.web.jpa.e_commerce_web_jpa.service.omset.OmsetService;
import com.ecommerce.web.jpa.e_commerce_web_jpa.service.produk.ProdukService;
import com.ecommerce.web.jpa.e_commerce_web_jpa.repositories.MemberRepository;
import com.ecommerce.web.jpa.e_commerce_web_jpa.repositories.TransaksiRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@Service
@Validated
@RequiredArgsConstructor
public class TransaksiServiceImpl implements TransaksiService {

    private final MemberRepository memberRepository;

    private final TransaksiRepository transaksiRepository;

    private final ProdukService produkService;

    private final OmsetService omsetService;

    private void reduceStack(Transaksi transaksi) {
        produkService.kurangiStock(transaksi.getTotalPembelian(), transaksi
                .getIdProduk().getId());
    }

    private void addJumlahPenjualan(Transaksi transaksi) {
        omsetService.tambahJumlahPenjualan(transaksi.getTotalPembelian(), transaksi
                .getIdProduk().getId());
    }

    private void breakRelation(Transaksi transaksi) {
        transaksi.getTokenMember().setListTransaksi(null);
        transaksi.getIdProduk().setListTransaksi(null);

        transaksi.setTokenMember(null);
        transaksi.setIdProduk(null);
    }

    @Override
    @Transactional
    public void insert(@Valid TransaksiInsertRequest transaksi) {

        Member member = new Member();
        Member tokenMember = memberRepository.findByToken(transaksi.getTokenMember())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "token not detected"));
        member.setId(tokenMember.getId());

        Produk produk = new Produk();
        produk.setId(transaksi.getIdProduk());

        Transaksi transaksiEntity = new Transaksi();
        transaksiEntity.setTotalPembelian(transaksi.getTotalPembelian());
        transaksiEntity.setPurchaseDate(LocalDate.now());
        transaksiEntity.setArrivalDate(LocalDate.now().plusDays(7));
        transaksiEntity.setTokenMember(member);
        transaksiEntity.setIdProduk(produk);

        transaksiRepository.save(transaksiEntity);
    }

    @Override
    @Transactional
    public void delete(@Positive int idTransaksi) {
        Transaksi transaksi = transaksiRepository.findById(idTransaksi)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "fail to delete transaksi"));

        reduceStack(transaksi);
        addJumlahPenjualan(transaksi);
        breakRelation(transaksi);

        transaksiRepository.delete(transaksi);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TransaksiResponse> findAll() {
        PageRequest paging = PageRequest.of(0, 5);

        return transaksiRepository.findAll(paging)
                .map(transaksi -> TransaksiResponse.builder()
                        .id(transaksi.getId())
                        .totalPembelian(transaksi.getTotalPembelian())
                        .purchaseDate(transaksi.getPurchaseDate())
                        .arrivalDate(transaksi.getArrivalDate())
                        .idMember(transaksi.getTokenMember().getId())
                        .idProduk(transaksi.getIdProduk().getId())
                        .build());
    }

}