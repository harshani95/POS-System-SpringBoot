package com.springbootacademy.pos.service;

import com.springbootacademy.pos.dto.ItemDTO;
import com.springbootacademy.pos.dto.request.ItemSaveRequestDTO;

public interface ItemService {
    String saveItem(ItemSaveRequestDTO itemSaveRequestDTO);

    ItemDTO getItemById(int itemId);
}
