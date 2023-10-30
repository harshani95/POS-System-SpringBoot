package com.springbootacademy.pos.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ItemPriceBalQtyUpdateDTO {
    private double balanceQty;
    private double sellingPrice;
}
