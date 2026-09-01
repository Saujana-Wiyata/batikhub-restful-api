package com.ecommerce.web.jpa.e_commerce_web_jpa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class WebResponse<T> {

    private int code;

    private T data;

    private String error;

    private PagingResponse paging;

}
