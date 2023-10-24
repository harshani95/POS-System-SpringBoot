package com.springbootacademy.pos.controller;

import com.springbootacademy.pos.dto.CustomerDTO;
import com.springbootacademy.pos.dto.request.CustomerUpdateDTO;
import com.springbootacademy.pos.service.impl.CustomerServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@CrossOrigin()

public class CustomerController {

    @Autowired
    private CustomerServiceIMPL customerService;

    @PostMapping
    public String saveCustomer(@RequestBody CustomerDTO customerDTO){
        //CustomerService customerService = new CustomerService();
        customerService.saveCustomer(customerDTO);
        return "saved";
    }

    @PutMapping(path = "/update")
    public String updateCustomer(@RequestBody CustomerUpdateDTO customerUpdateDTO){
        String message =  customerService.updateCustomer(customerUpdateDTO);
        return message;
    }

    @GetMapping(
            path = "/get-by-id",
            params = "id"
    )
    public  CustomerDTO getCustomerById(@RequestParam(value = "id") int cutomerId){
        CustomerDTO customerDTO = customerService.getCustomerById(cutomerId);
        return customerDTO;
    }

    @DeleteMapping(path = "/delete-customer/{id}")
    public  String deleteCustomer(@PathVariable(value = "id") int cutomerId){
        String deleted = customerService.deleteCustomer(cutomerId);
        return deleted;
    }

    @GetMapping(path = "get-all-customers")
    public List<CustomerDTO> getAllCustomers(){
        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();
        return customerDTOS ;
    }

    @GetMapping(path = "get-all-customers-by-active-state/{status}")
    public List<CustomerDTO> getAllCustomersByActiveStatus(@PathVariable(value = "status") boolean activeStatus){
        List<CustomerDTO> customerDTOS = customerService.getAllCustomersByActiveStatus(activeStatus);
        return customerDTOS ;
    }

}
