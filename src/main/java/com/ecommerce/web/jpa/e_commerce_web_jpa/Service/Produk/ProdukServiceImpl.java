package com.ecommerce.web.jpa.e_commerce_web_jpa.Service.Produk;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.ecommerce.web.jpa.e_commerce_web_jpa.Dto.Produk.ProdukInsertDTO;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Dto.Produk.ProdukUpdateDTO;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Produk;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Enum.ProductCategory;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Repositories.ProdukRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@Service
@Validated
@RequiredArgsConstructor
public class ProdukServiceImpl implements ProdukService {

    private final ProdukRepository produkRepository;

    @Override
    public void insert(@Valid ProdukInsertDTO produk) {
        Produk produkEntity = new Produk();
        produkEntity.setId(produk.getId());
        produkEntity.setNama(produk.getNama());
        produkEntity.setStock(produk.getStock());
        produkEntity.setHarga(produk.getHarga());
        produkEntity.setProductCategory(ProductCategory
                .valueOf(produk.getProductCategory()));
        try {
            produkEntity.setGambar(produk.getGambar().getBytes());
        } catch (IOException e) {
            throw new RuntimeException("gambar tidak bisa di-upload");
        }

        produkRepository.save(produkEntity);
    }

    @Override
    public Produk findById(@NotBlank String id) {
        return produkRepository.findById(id).orElse(null);
    }

    @Override
    public void kurangiStock(@Positive int jmlhPembelian, @NotBlank String idProduk) {
        produkRepository.reduceStock(jmlhPembelian, idProduk);
    }

    @Override
    public List<Produk> findByNama(@NotBlank String nameProduk) {
        return produkRepository.findAllByNamaLike("%" + nameProduk + "%");
    }

    @Override
    public List<Produk> findAll() {
        return produkRepository.findAll();
    }

    @Override
    public Produk update(@NotBlank String id, ProdukUpdateDTO produk) {

        Produk produkFindId = produkRepository.findById(id).orElse(null);

        if (!produk.getNama().isBlank())
            produkFindId.setNama(produk.getNama());

        if ((produk.getHarga() != produkFindId.getHarga()) &&
                produk.getHarga() > 0)
            produkFindId.setHarga(produk.getHarga());

        if (produk.getStock() != null &&
                (produk.getStock() != produkFindId.getStock()))
            produkFindId.setStock(produkFindId.getStock() +
                    produk.getStock());

        if (!produk.getProductCategory().isBlank())
            produkFindId.setProductCategory(ProductCategory
                    .valueOf(produk.getProductCategory()));

        if (produk.getGambar() != null && !produk.getGambar().isEmpty()) {
            try {
                produkFindId.setGambar(produk.getGambar().getBytes());
            } catch (IOException e) {
                throw new RuntimeException("fail update gambar");
            }
        }

        return produkRepository.save(produkFindId);
    }

    @Override
    public void delete(@NotBlank String id) {
        Produk produk = produkRepository.findById(id).orElse(null);
        produkRepository.delete(produk);
    }

}
