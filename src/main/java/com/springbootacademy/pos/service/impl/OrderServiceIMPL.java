package com.springbootacademy.pos.service.impl;

import com.springbootacademy.pos.dto.paginated.PaginatedResponseOrderDetails;
import com.springbootacademy.pos.dto.queryinterface.OrderDetailInterface;
import com.springbootacademy.pos.dto.request.RequestOrderDetailsSaveDTO;
import com.springbootacademy.pos.dto.response.ResponseOrderDetailsDTO;
import com.springbootacademy.pos.entity.Order;
import com.springbootacademy.pos.entity.OrderDetails;
import com.springbootacademy.pos.exeption.NotFoundException;
import com.springbootacademy.pos.repo.CustomerRepo;
import com.springbootacademy.pos.repo.ItemRepo;
import com.springbootacademy.pos.repo.OrderDetailsRepo;
import com.springbootacademy.pos.repo.OrderRepo;
import com.springbootacademy.pos.service.CustomerService;
import com.springbootacademy.pos.service.OrderService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceIMPL implements OrderService {
    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private OrderDetailsRepo orderDetailsRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public String saveOrder(RequestOrderDetailsSaveDTO requestOrderDetailsSaveDTO) {
        Order order = new Order(
                customerRepo.getById(requestOrderDetailsSaveDTO.getCustomer()),
                requestOrderDetailsSaveDTO.getDate(),
                requestOrderDetailsSaveDTO.getTotal()
        );
        orderRepo.save(order);
        if (orderRepo.existsById(order.getOrderId())) {
            List<OrderDetails> orderDetails = modelMapper.
                    map(requestOrderDetailsSaveDTO.getRequestOrderSaveDTOS(), new TypeToken<List<OrderDetails>>() {
                    }.getType());
            for (int i = 0; i < orderDetails.size(); i++) {
                orderDetails.get(i).setOrders(order);
                orderDetails.get(i).setItems(itemRepo.getById(requestOrderDetailsSaveDTO.getCustomer()));
            }
            if (orderDetails.size() > 0) {
                orderDetailsRepo.saveAll(orderDetails);
            }
            return "Saved!";
        }
        throw new NotFoundException("id does not exist!!!");
    }

    @Override
    public PaginatedResponseOrderDetails getAllOrderDetails(boolean status, int page, int size) {
        List<OrderDetailInterface> orderDetailsDTOS = orderRepo.getAllOrderDetails(status, (Pageable) PageRequest.of(page, size));

        List<ResponseOrderDetailsDTO> list = new ArrayList<>();
        for(OrderDetailInterface o: orderDetailsDTOS){
            ResponseOrderDetailsDTO r = new ResponseOrderDetailsDTO(
                    o.getCustomerName(),
                    o.getCustomerAddress(),
                    o.getContactNumber(),
                    o.getDate(),
                    o.getTotal()
            );
            list.add(r);
        }
        PaginatedResponseOrderDetails paginatedResponseOrderDetails = new PaginatedResponseOrderDetails(
                list,
                orderRepo.countAllOrderDetails(status)
        );
        return  paginatedResponseOrderDetails;
    }


}
