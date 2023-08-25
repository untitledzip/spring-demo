package com.example.repository;

import com.example.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item>, ItemRepositoryCustom {
    
    /* 쿼리 메소드를 이용한 상품 조회하기
       - 쿼리 메소드를 이용할 때 가장 많이 사용되는 문법으로 find를 사용
       - 엔티티 이름은 생략 가능하며, By 뒤에는 검색할 때 사용할 변수의 이름을 적음
    */

    //상품명으로 데이터를 조회하기 위해 By 뒤에 필드명인 ItemNm을 메소드의 이름에 붙여줌
    List<Item> findByItemNm(String itemNm);

    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);

    List<Item> findByPriceLessThan(Integer price);

    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);

    /*
        Spring Data JPA에서 제공하는 @Query 어노테이션
        객체지향 쿼리 언어를 통해 복잡한 쿼리도 처리가 가능(JPQL, Java Persistance Query Language)
        SQL을 추상화해서 사용하기 때문에 특정 데이터베이스 SQL에 의존하지 않음
        데이터베이스가 변경되어도 애플리케이션이 영향을 받지 않음

     */
    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);
    //@Param으로 파라미터로 넘어온 값을 JPQL에 들어갈 변수로 지정해 줄 수 있음

    @Query(value = "select * from item i where i.item_detail like %:itemDetail% order by i.price desc", nativeQuery = true)
    List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail);


}
