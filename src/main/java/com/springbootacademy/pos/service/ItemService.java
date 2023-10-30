package com.springbootacademy.pos.service;

import com.springbootacademy.pos.dto.ItemDTO;
import com.springbootacademy.pos.dto.paginated.PaginatedResponseItemDTO;
import com.springbootacademy.pos.dto.request.ItemPriceBalQtyUpdateDTO;
import com.springbootacademy.pos.dto.request.ItemSaveRequestDTO;
import com.springbootacademy.pos.dto.response.ItemGetResponseDTO;

import java.util.List;

public interface ItemService {
    String saveItem(ItemSaveRequestDTO itemSaveRequestDTO);

    ItemDTO getItemById(int itemId);

    List<ItemGetResponseDTO> getItemByNameAndStatusByMapstruct(String name);

    List<ItemDTO> getItems();

    void deleteItemById(int id);

    String updateItemPriceBalQty(int id, ItemPriceBalQtyUpdateDTO itemPriceBalQtyUpdateDTO);

    PaginatedResponseItemDTO getAllItems(int page, int size);

    PaginatedResponseItemDTO getItemsByState(int page, int size, boolean status);
}
