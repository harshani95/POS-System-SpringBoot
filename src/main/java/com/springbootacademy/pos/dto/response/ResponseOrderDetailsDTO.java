package com.springbootacademy.pos.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseOrderDetailsDTO {
    //CUSTOMER
    private  String customerName;
    private String customerAddress;
    private ArrayList contactNumbers;

    //ORDER
    private Date date;
    private double total;

}
