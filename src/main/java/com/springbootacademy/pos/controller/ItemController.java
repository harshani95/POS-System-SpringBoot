package com.springbootacademy.pos.controller;

import com.springbootacademy.pos.dto.ItemDTO;
import com.springbootacademy.pos.dto.request.ItemSaveRequestDTO;
import com.springbootacademy.pos.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/item")
@CrossOrigin()
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping(path = "/save")
    public String saveItem(@RequestBody ItemSaveRequestDTO itemSaveRequestDTO) {
        String message = itemService.saveItem(itemSaveRequestDTO);
        return "saved";
    }

    @GetMapping(path = "/get-by-id", params = "id")
    public ItemDTO getItemById(@RequestParam(value = "id") int itemId) {
        ItemDTO itemDTO = itemService.getItemById(itemId);
        return itemDTO;
    }
}
