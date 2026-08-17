package com.ecommerce.web.jpa.e_commerce_web_jpa.Service.Omset;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.ecommerce.web.jpa.e_commerce_web_jpa.Dto.Omset.OmsetDTO;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Omset;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Produk;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Repositories.OmsetRespository;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Repositories.ProdukRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@Service
@Validated
@RequiredArgsConstructor
public class OmsetServiceImpl implements OmsetService {

    private final OmsetRespository omsetRespository;

    private final ProdukRepository produkRepository;

    @Override
    @Transactional
    public void insert(@Valid OmsetDTO omset) {

        Produk produk = produkRepository.findById(omset.getIdProduk().getId())
                .orElse(null);

        Omset omsetEntity = new Omset();
        omsetEntity.setIdProduk(produk);
        omsetEntity.setJumlahPenjualan(omset.getJumlahPenjualan());

        omsetRespository.save(omsetEntity);
    }

    private void breakRelation(Omset omset) {
        omset.getIdProduk().setOmset(null);
        omset.setIdProduk(null);
    }

    @Override
    public void delete(@Positive int id) {
        Omset omset = omsetRespository.findById(id).orElse(null);

        breakRelation(omset);

        omsetRespository.save(omset);

        omsetRespository.delete(omset);
    }

    @Override
    public void tambahJumlahPenjualan(@Positive int jumlahPembelian, @NotBlank String idProduk) {
        omsetRespository.tambahJumlahPenjualan(jumlahPembelian, idProduk);
    }

    @Override
    public Double jumlahHargaPerProduk(@NotBlank String idProduk) {
        return omsetRespository.jumlahHargaPerProduk(idProduk);
    }

    @Override
    public Double totalKeseluruhanOmset() {
        return omsetRespository.jumlahOmset();
    }

    @Override
    public Integer totalProdukTerjual() {
        return omsetRespository.jumlahProdukTerjual();
    }

    @Override
    public List<Omset> findAll() {
        return omsetRespository.findAll();
    }

}
