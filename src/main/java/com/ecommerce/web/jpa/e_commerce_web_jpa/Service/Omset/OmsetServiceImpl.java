package com.ecommerce.web.jpa.e_commerce_web_jpa.service.omset;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.web.jpa.e_commerce_web_jpa.entities.Omset;
import com.ecommerce.web.jpa.e_commerce_web_jpa.entities.Produk;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.omset.OmsetPerProduk;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.omset.OmsetRequest;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.omset.OmsetResponse;
import com.ecommerce.web.jpa.e_commerce_web_jpa.repositories.OmsetRespository;
import com.ecommerce.web.jpa.e_commerce_web_jpa.repositories.ProdukRepository;

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
    public void insert(@Valid OmsetRequest omset) {

        Produk produk = produkRepository.findById(omset.getIdProduk())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_GATEWAY,
                        "fail to calculate into omset"));

        Omset omsetEntity = new Omset();
        omsetEntity.setIdProduk(produk);
        omsetEntity.setJumlahPenjualan(omset.getJumlahPenjualan());

        omsetRespository.save(omsetEntity);
    }

    @Override
    public void tambahJumlahPenjualan(@Positive int jumlahPembelian, @NotBlank String idProduk) {
        omsetRespository.tambahJumlahPenjualan(jumlahPembelian, idProduk);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OmsetResponse> findAll() {

        PageRequest page = PageRequest.of(0, 5);

        return omsetRespository.findAll(page)
                .map(omset -> OmsetResponse.builder()
                        .omset(omsetRespository.jumlahOmset())
                        .totalProductSoldout(omsetRespository.jumlahProdukTerjual())
                        .omsetPerproduct(
                                new OmsetPerProduk(
                                        omset.getIdProduk().getId(),
                                        omset.getIdProduk().getNama(),
                                        omsetRespository.jumlahHargaPerProduk(omset.getIdProduk().getId()),
                                        omsetRespository.jumlahPerProdukTerjual(omset.getIdProduk().getId())))
                        .build());
    }

}
