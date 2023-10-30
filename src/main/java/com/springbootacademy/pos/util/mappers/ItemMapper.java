package com.springbootacademy.pos.util.mappers;

import com.springbootacademy.pos.dto.ItemDTO;
import com.springbootacademy.pos.dto.response.ItemGetResponseDTO;
import com.springbootacademy.pos.entity.Item;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    List<ItemGetResponseDTO> entityListToDTOList(List<Item> items);

    List<ItemDTO> pageToListDto(Page<Item> getItemsByState);

    List<ItemDTO> listEntityToListDto(List<Item> getItems);
}
