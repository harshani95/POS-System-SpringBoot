package com.springbootacademy.pos.service.impl;

import com.springbootacademy.pos.dto.CustomerDTO;
import com.springbootacademy.pos.dto.request.CustomerUpdateDTO;
import com.springbootacademy.pos.entity.Customer;
import com.springbootacademy.pos.exeption.NotFoundException;
import com.springbootacademy.pos.repo.CustomerRepo;
import com.springbootacademy.pos.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceIMPL implements CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public String saveCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer(

                customerDTO.getCustomerId(),
                customerDTO.getCustomerName(),
                customerDTO.getCustomerAddress(),
                customerDTO.getContactNumbers(),
                customerDTO.getNic(),
                customerDTO.isActiveState()
        );

        customerRepo.save(customer);

        return customer.getCustomerName() + "saved";
    }


    @Override
    public String updateCustomer(CustomerUpdateDTO customerUpdateDTO) {
        if(customerRepo.existsById(customerUpdateDTO.getCustomerId())){
            Customer customer = customerRepo.getReferenceById(customerUpdateDTO.getCustomerId());

            customer.setCustomerName(customerUpdateDTO.getCustomerName());
            customer.setCustomerAddress(customerUpdateDTO.getCustomerAddress());
            customer.setNic(customerUpdateDTO.getNic());

            customerRepo.save(customer);
            return  customerUpdateDTO.getCustomerName() + "Updated";
        }
        else {
           // throw new RuntimeException("No data Found for that ID");
            throw new NotFoundException("No data Found for that ID");
        }
    }

    @Override
    public CustomerDTO getCustomerById(int cutomerId) {
        if(customerRepo.existsById(cutomerId)) {
            Customer customer = customerRepo.getReferenceById(cutomerId);

            CustomerDTO customerDTO = new CustomerDTO(
                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getCustomerAddress(),
                    customer.getContactNumbers(),
                    customer.getNic(),
                    customer.isActiveState()
            );

            return customerDTO;
        }
        else {
            throw new RuntimeException("No Customer");
        }
    }

    @Override
    public String deleteCustomer(int customerId) {
        if(customerRepo.existsById(customerId)){
            customerRepo.deleteById(customerId);
            return customerId + " deleted";
        }
        else{
            throw new RuntimeException("no customer for delete");
        }

    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customerDTOList = customerRepo.findAll();
        List <CustomerDTO> customerDTOS = new ArrayList<>();

        for(Customer customer : customerDTOList){
            CustomerDTO customerDTO = new CustomerDTO(
                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getCustomerAddress(),
                    customer.getContactNumbers(),
                    customer.getNic(),
                    customer.isActiveState()
            );
            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }

    @Override
    public List<CustomerDTO> getAllCustomersByActiveStatus(boolean activeStatus) {
        List<Customer> customerDTOList = customerRepo.findAllByActiveState(activeStatus);
        List <CustomerDTO> customerDTOS = new ArrayList<>();

        for(Customer customer : customerDTOList){
            CustomerDTO customerDTO = new CustomerDTO(
                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getCustomerAddress(),
                    customer.getContactNumbers(),
                    customer.getNic(),
                    customer.isActiveState()
            );
            customerDTOS.add(customerDTO);
        }
        return customerDTOS;

    }

    @Override
    public List<CustomerDTO> getByName(String customerName) {
        return null;
    }


}
