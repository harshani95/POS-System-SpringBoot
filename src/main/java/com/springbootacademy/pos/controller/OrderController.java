package com.springbootacademy.pos.controller;

import com.springbootacademy.pos.dto.paginated.PaginatedResponseOrderDetails;
import com.springbootacademy.pos.dto.request.RequestOrderDetailsSaveDTO;
import com.springbootacademy.pos.service.OrderService;
import com.springbootacademy.pos.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;

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
                new StandardResponse(201, "Item Successfully Saved", id),
                HttpStatus.CREATED
        );
    }

    @GetMapping(
            params = {"stateType", "page", "size"},
            path = {"/get-order-details"}
    )
    public ResponseEntity<StandardResponse> getAllOrderDetails(
            @RequestParam(value = "stateType") String stateType,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") @Max(50) int size
    )
    {
        PaginatedResponseOrderDetails p = null;
        if(stateType.equalsIgnoreCase("active") | stateType.equalsIgnoreCase("inactive")){
            boolean status = stateType.equalsIgnoreCase("active") ? true : false;
            p = orderService.getAllOrderDetails(status,page,size);
        }
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"SUUCCESS",p),
                HttpStatus.OK
        );
    }


}
