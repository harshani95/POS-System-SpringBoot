package com.springbootacademy.pos.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestOrderSaveDTO {
    private String itemName;
    private double qty;
    private double amount;
    private int orders;
    private int items;
}
