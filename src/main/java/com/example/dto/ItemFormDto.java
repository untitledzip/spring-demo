package com.example.dto;

import com.example.constant.ItemSellStatus;
import com.example.entity.Item;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ItemFormDto {

    private Long id;

    @NotBlank(message = "상품명은 필수 입력 값입니다.")
    private String itemNm;

    @NotNull(message = "가격은 필수 입력 값입니다.")
    private Integer price;

    @NotBlank(message = "상품 설명은 필수 입력 값입니다.")
    private String itemDetail;

    @NotNull(message = "재고는 필수 입력 값입니다.")
    private Integer stockNumber;

    private ItemSellStatus itemSellStatus;

    private List<ItemImgDto> itemImgDtoList = new ArrayList<>(); //상품 저장 후 수정할 때 상품 이미지 정보를 보는 리스트

    private List<Long> itemImgIds = new ArrayList<>(); //상품의 이미지 아이디를 저장하는 리스트, 수정 시에 이미지 아이디를 담아둘 용도

    private static ModelMapper modelMapper = new ModelMapper();
    //modelMapper를 이용하여 엔티티 객체와 DTO 객체 간의 데이터를 복사하여 복사한 객체를 반환해 주는 메소드
    public Item createItem(){
        return modelMapper.map(this, Item.class);
    }

    public static ItemFormDto of(Item item){
        return modelMapper.map(item, ItemFormDto.class);
    }
}
