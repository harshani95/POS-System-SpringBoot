package com.springbootacademy.pos.service.impl;

import com.springbootacademy.pos.dto.CustomerDTO;
import com.springbootacademy.pos.dto.ItemDTO;
import com.springbootacademy.pos.dto.request.ItemSaveRequestDTO;
import com.springbootacademy.pos.entity.Customer;
import com.springbootacademy.pos.entity.Item;
import com.springbootacademy.pos.repo.ItemRepo;
import com.springbootacademy.pos.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceIMPL implements ItemService {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public String saveItem(ItemSaveRequestDTO itemSaveRequestDTO) {
       Item item = modelMapper.map(itemSaveRequestDTO,Item.class);
       itemRepo.save(item);
        return item.getItemName() + "saved";
    }

    @Override
    public ItemDTO getItemById(int itemId) {

        if(itemRepo.existsById(itemId)) {
            Item item = itemRepo.getReferenceById(itemId);
            ItemDTO itemDTO = modelMapper.map(item,ItemDTO.class);

            return itemDTO;
        }
        else {
            throw new RuntimeException("No Customer");
        }

    }
}

