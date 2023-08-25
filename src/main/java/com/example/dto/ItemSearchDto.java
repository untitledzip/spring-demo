package com.example.dto;

import com.example.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemSearchDto {

    /*
        all 상품 등록일 전체
        1d 최근 하루 동안 등록된 상품
        1w 최근 일주일 동안
        1m 최근 한 달 동안
        6m 최근 6개월 동안
     */
    private String searchDateType;


    private ItemSellStatus searchSellStatus;

    /*
        상품 조회할 때 어떤 유형으로 조회할지
            1. itemNm 상품명
            2. createdBy 상품 등록자 아이디
     */
    private String searchBy;

    private String searchQuery = "";
}
