package com.ecommerce.web.jpa.e_commerce_web_jpa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PagingResponse {

    private int currentPage;
    private int totalPage;
    private int size;
    private int totalElements;

}
