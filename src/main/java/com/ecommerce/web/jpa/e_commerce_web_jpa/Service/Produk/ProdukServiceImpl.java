package com.ecommerce.web.jpa.e_commerce_web_jpa.service.produk;

import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.web.jpa.e_commerce_web_jpa.entities.Produk;
import com.ecommerce.web.jpa.e_commerce_web_jpa.entities.enums.ProductCategory;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.produk.ProdukInsertRequest;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.produk.ProdukResponse;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.produk.ProdukUpdateRequest;
import com.ecommerce.web.jpa.e_commerce_web_jpa.repositories.ProdukRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@Service
@Validated
@RequiredArgsConstructor
public class ProdukServiceImpl implements ProdukService {

    private final ProdukRepository produkRepository;

    private String checkId(String id) {
        if (produkRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "ID already exist");
        } else {
            return id;
        }
    }

    @Override
    @Transactional
    public void insert(@Valid ProdukInsertRequest produk) {
        Produk produkEntity = new Produk();
        produkEntity.setId(checkId(produk.getId()));
        produkEntity.setNama(produk.getNama());
        produkEntity.setStock(produk.getStock());
        produkEntity.setHarga(produk.getHarga());
        produkEntity.setProductCategory(ProductCategory
                .valueOf(produk.getProductCategory()));
        try {
            produkEntity.setGambar(produk.getGambar().getBytes());
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "gambar tidak bisa di-upload");
        }

        produkRepository.save(produkEntity);
    }

    @Override
    @Transactional
    public ProdukResponse findById(@NotBlank String id) {
        Produk produk = produkRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "your product not found"));

        return new ProdukResponse(produk.getId(), produk.getNama(), produk.getStock(),
                produk.getHarga(), produk.getProductCategory(), produk.getGambar());
    }

    @Override
    public void kurangiStock(@Positive int jmlhPembelian, @NotBlank String idProduk) {
        produkRepository.reduceStock(jmlhPembelian, idProduk);
    }

    @Override
    public Page<ProdukResponse> findByNama(@NotBlank String nameProduk) {

        PageRequest paging = PageRequest.of(0, 6);

        return produkRepository.findAllByNamaLike("%" + nameProduk + "%", paging)
                .map(produk -> ProdukResponse.builder()
                        .idProduct(produk.getId())
                        .nama(produk.getNama())
                        .stock(produk.getStock())
                        .harga(produk.getHarga())
                        .productCategory(produk.getProductCategory())
                        .gambar(produk.getGambar())
                        .build());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProdukResponse> findAll() {

        PageRequest paging = PageRequest.of(0, 6);

        return produkRepository.findAll(paging)
                .map(produk -> ProdukResponse.builder()
                        .idProduct(produk.getId())
                        .nama(produk.getNama())
                        .stock(produk.getStock())
                        .harga(produk.getHarga())
                        .productCategory(produk.getProductCategory())
                        .gambar(produk.getGambar())
                        .build());
    }

    @Override
    public ProdukResponse update(@NotBlank String id, ProdukUpdateRequest produk) {

        Produk produkFindId = produkRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found"));

        if (!produk.getNama().isBlank())
            produkFindId.setNama(produk.getNama());

        if ((produk.getHarga() != produkFindId.getHarga()) &&
                produk.getHarga() > 0)
            produkFindId.setHarga(produk.getHarga());

        if (produk.getStock() != null &&
                (produk.getStock() != produkFindId.getStock()))
            produkFindId.setStock(produkFindId.getStock() +
                    produk.getStock());

        if (!produk.getProductCategory().isBlank()) {
            try {
                produkFindId.setProductCategory(ProductCategory
                        .valueOf(produk.getProductCategory()));
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "category is invalid");
            }
        }

        if (produk.getGambar() != null && !produk.getGambar().isEmpty()
                && produk.getGambar().getSize() > 100) {
            try {
                produkFindId.setGambar(produk.getGambar().getBytes());
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "fail to update gambar");
            }
        }
        Produk save = produkRepository.save(produkFindId);

        return new ProdukResponse(save.getId(), save.getNama(), save.getStock(),
                save.getHarga(), save.getProductCategory(), save.getGambar());
    }

    @Override
    public void delete(@NotBlank String id) {
        Produk produk = produkRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "fail to delete the product"));
        produkRepository.delete(produk);
    }

}
