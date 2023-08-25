package com.example.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class MainItemDto {

    private Long id;

    private String itemNm;

    private String itemDetail;

    private String imgUrl;

    private Integer price;

    /*
        @QueryProjection 을 이용하여 상품 조회 시 DTO 객체로 결과값을 반환 받는 방법
         - Item 객체로 값을 받은 후 DTO 클래스로 변환하는 과정 없이 바로 DTO 객체를 가져올 수 있음
    
     */
    @QueryProjection
    public MainItemDto(Long id, String itemNm, String itemDetail, String imgUrl, Integer price){
        this.id = id;
        this.itemNm = itemNm;
        this.itemDetail = itemDetail;
        this.price = price;
    }
}
