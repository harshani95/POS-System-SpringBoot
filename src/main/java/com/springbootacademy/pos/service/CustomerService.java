package com.springbootacademy.pos.service;

import com.springbootacademy.pos.dto.CustomerDTO;
import com.springbootacademy.pos.dto.request.CustomerUpdateDTO;

import java.util.List;

public interface CustomerService {
    public String saveCustomer(CustomerDTO customerDTO);


    String updateCustomer(CustomerUpdateDTO customerUpdateDTO);

    CustomerDTO getCustomerById(int cutomerId);

    String deleteCustomer(int cutomerId);

    List<CustomerDTO> getAllCustomers();


    List<CustomerDTO> getAllCustomersByActiveStatus(boolean activestatus);

    List<CustomerDTO> getByName(String customerName);
}
