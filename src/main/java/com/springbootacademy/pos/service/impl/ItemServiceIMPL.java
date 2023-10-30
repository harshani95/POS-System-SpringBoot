package com.springbootacademy.pos.service.impl;

import com.springbootacademy.pos.dto.CustomerDTO;
import com.springbootacademy.pos.dto.ItemDTO;
import com.springbootacademy.pos.dto.paginated.PaginatedResponseItemDTO;
import com.springbootacademy.pos.dto.request.ItemPriceBalQtyUpdateDTO;
import com.springbootacademy.pos.dto.request.ItemSaveRequestDTO;
import com.springbootacademy.pos.dto.response.ItemGetResponseDTO;
import com.springbootacademy.pos.entity.Customer;
import com.springbootacademy.pos.entity.Item;
import com.springbootacademy.pos.exeption.NotFoundException;
import com.springbootacademy.pos.repo.ItemRepo;
import com.springbootacademy.pos.service.ItemService;
import com.springbootacademy.pos.util.mappers.ItemMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceIMPL implements ItemService {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ItemMapper itemMapper;

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

    @Override
    public List<ItemGetResponseDTO> getItemByNameAndStatusByMapstruct(String name) {
       boolean b = true;
       List<Item> items = itemRepo.findAllByItemNameEqualsAndActiveStateEquals(name,b);
        if(items.size()>0){
            List<ItemGetResponseDTO> itemGetResponseDTOS = itemMapper.entityListToDTOList(items);
            return itemGetResponseDTOS;
        }else {
            throw new RuntimeException("No Item to show");
        }
    }

    @Override
    public List<ItemDTO> getItems() {
        List<Item> getItems = itemRepo.findAll();


//        List<ItemDTO> itemDTOS = modelMapper.
//                map(getItems, new TypeToken<List<ItemDTO>>() {
//                }.getType());

        List<ItemDTO> itemDTO = itemMapper.listEntityToListDto(getItems);
        return itemDTO;
    }

    @Override
    public void deleteItemById(int id) {
        if (itemRepo.existsById(id)){
            itemRepo.deleteById(id);

        }else {
            throw new NotFoundException("Item id does not exist!!!");
        }

    }

    @Override
    public String updateItemPriceBalQty(int id, ItemPriceBalQtyUpdateDTO itemPriceBalQtyUpdateDTO) {
        if (itemRepo.existsById(id)) {
            Item item = itemRepo.getById(id);
//            item.setBalanceQty(itemPriceBalQtyUpdateDTO.getBalanceQty());
//            item.setSellingPrice(itemPriceBalQtyUpdateDTO.getSellingPrice());
//
//            itemRepo.save(item);
//            return item.getItemName();
            itemRepo.updateBalQtySellingPrice(itemPriceBalQtyUpdateDTO.getBalanceQty(), itemPriceBalQtyUpdateDTO.getSellingPrice(), id);

            return item.getItemName();
        }
        throw new NotFoundException("Id does not exist");
    }

    @Override
    public PaginatedResponseItemDTO getAllItems(int page, int size) {
        Page<Item> getAllItems = itemRepo.findAll(PageRequest.of(page, size));

        return new PaginatedResponseItemDTO(
                itemMapper.pageToListDto(getAllItems),
                itemRepo.count()
        );
    }

    @Override
    public PaginatedResponseItemDTO getItemsByState(int page, int size, boolean status) {
        Page<Item> getItemsByState = itemRepo.findAllByActiveStateEquals(status,PageRequest.of(page, size));
        return new PaginatedResponseItemDTO(
                itemMapper.pageToListDto(getItemsByState),
                20
        );
    }

}

