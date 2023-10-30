package com.springbootacademy.pos.controller;

import com.springbootacademy.pos.dto.request.RequestOrderDetailsSaveDTO;
import com.springbootacademy.pos.service.OrderService;
import com.springbootacademy.pos.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/order")

public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(path = "/save")
    public ResponseEntity<StandardResponse> saveOrder(@RequestBody RequestOrderDetailsSaveDTO requestOrderDetailsSaveDTO) {
        String id = orderService.saveOrder(requestOrderDetailsSaveDTO);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "Sucesssed", id), HttpStatus.OK
        );
    }

}
