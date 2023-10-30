package com.springbootacademy.pos.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestOrderDetailsSaveDTO {
    private int customer;
    private Date date;
    private double total;
    private List<RequestOrderSaveDTO> requestOrderSaveDTOS;
}
