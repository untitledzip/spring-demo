package com.example.service;

import com.example.constant.ItemSellStatus;
import com.example.dto.ItemFormDto;
import com.example.entity.Item;
import com.example.entity.ItemImg;
import com.example.repository.ItemImgRepository;
import com.example.repository.ItemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemImgRepository itemImgRepository;

    List<MultipartFile> createMultipartFiles()throws Exception {

        List<MultipartFile> multipartFilesList = new ArrayList<>();

        for(int i=0;i<5;i++){
            String path = "D:/spring-demo/item/";
            String imageName = "image" + i + ".jpg";
            MockMultipartFile multipartFile = new MockMultipartFile(path, imageName,
                    "image/jpg", new byte[]{1,2,3,4});
            multipartFilesList.add(multipartFile);
        }

        return multipartFilesList;
    }

    @Test
    @DisplayName("상품 등록 테스트")
    @WithMockUser(username = "admin123", roles="ADMIN")
    void savedItem() throws Exception{
        ItemFormDto itemFormDto = new ItemFormDto();
        itemFormDto.setItemNm("테스트 상품1");
        itemFormDto.setItemSellStatus(ItemSellStatus.SELL);
        itemFormDto.setPrice(10000);
        itemFormDto.setStockNumber(100);

        List<MultipartFile> multipartFileList = createMultipartFiles();
        Long itemId = itemService.saveItem(itemFormDto, multipartFileList);

        List<ItemImg> itemImgList = itemImgRepository.findByItemIdOrderByIdAsc(itemId);
        Item item = itemRepository.findById(itemId)
                .orElseThrow(EntityNotFoundException::new);

        assertEquals(itemFormDto.getItemNm(), item.getItemNm());
        assertEquals(itemFormDto.getItemSellStatus(), item.getItemSellStatus());
        assertEquals(itemFormDto.getItemDetail(), item.getItemDetail());
        assertEquals(itemFormDto.getPrice(), item.getPrice());
        assertEquals(itemFormDto.getStockNumber(), item.getStockNumber());
        assertEquals(multipartFileList.get(0).getOriginalFilename(), itemImgList.get(0).getOriImgName());
    }

}