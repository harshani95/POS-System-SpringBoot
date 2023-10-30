package com.springbootacademy.pos.service;

import com.springbootacademy.pos.dto.request.RequestOrderDetailsSaveDTO;

public interface OrderService {
    String saveOrder(RequestOrderDetailsSaveDTO requestOrderDetailsSaveDTO);
}
