package com.springbootacademy.pos.controller;

import com.springbootacademy.pos.dto.CustomerDTO;
import com.springbootacademy.pos.dto.request.CustomerUpdateDTO;
import com.springbootacademy.pos.exeption.NotFoundException;
import com.springbootacademy.pos.service.impl.CustomerServiceIMPL;
import com.springbootacademy.pos.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@CrossOrigin()

public class CustomerController {

    @Autowired
    private CustomerServiceIMPL customerService;

    @PostMapping(path = "/save")
    public ResponseEntity<StandardResponse> addCustomer(@RequestBody CustomerDTO customerDTO) {
        String id = customerService.saveCustomer(customerDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Successfully added",id), HttpStatus.CREATED
        );
    }


    @PutMapping(path = "/update")
    public ResponseEntity<StandardResponse> updateCustomer(@RequestBody CustomerUpdateDTO customerUpdateDTO) {
        String updated = customerService.updateCustomer(customerUpdateDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Updated Successfully",updated), HttpStatus.OK
        );
    }

    @GetMapping(path = "/get-by-id", params = "id")
    public ResponseEntity<StandardResponse> getCustomerById(@RequestParam(value = "id") int id) {
        CustomerDTO customerDTO = customerService.getCustomerById(id);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"",customerDTO),HttpStatus.OK
        );
    }

    @GetMapping(path = "/get-by-name",
            params = "name")
    public ResponseEntity<StandardResponse> customerGetByName(@RequestParam(value = "name") String customerName) throws NotFoundException {
        List<CustomerDTO> getCustomer = customerService.getByName(customerName);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"",getCustomer),HttpStatus.OK
        );
    }

    @DeleteMapping(path = "/delete-customer/{id}")
    public ResponseEntity<StandardResponse> deleteCustomer(@PathVariable(value = "id") int id) throws NotFoundException {
        customerService.deleteCustomer(id);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Successfully Deleted!!",null),HttpStatus.OK
        );
    }

    @GetMapping(path = "get-all-customers")
    public ResponseEntity<StandardResponse> getAllCustomers() {
        List<CustomerDTO> allCustomers = customerService.getAllCustomers();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"All Customers",allCustomers),HttpStatus.OK
        );
    }

    @GetMapping(path = "get-all-customers-by-active-state/{status}")
    public ResponseEntity<StandardResponse> getAllCustomersByActiveStatus(@PathVariable(value = "status") boolean activeStatus){
        List<CustomerDTO> customerDTOS = customerService.getAllCustomersByActiveStatus(activeStatus);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"All Customers",customerDTOS),HttpStatus.OK
        );
    }

}
