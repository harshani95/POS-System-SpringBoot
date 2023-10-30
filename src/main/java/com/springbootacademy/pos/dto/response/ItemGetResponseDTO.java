package com.springbootacademy.pos.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemGetResponseDTO {
    private String itemName;
    private double balanceQty;
    private double sellingPrice;
    private boolean activeState;
}
