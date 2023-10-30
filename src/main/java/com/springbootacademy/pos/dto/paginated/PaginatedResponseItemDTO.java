package com.springbootacademy.pos.dto.paginated;

import com.springbootacademy.pos.dto.ItemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginatedResponseItemDTO {
    private List<ItemDTO> listItems;
    private long dataCount;
}
