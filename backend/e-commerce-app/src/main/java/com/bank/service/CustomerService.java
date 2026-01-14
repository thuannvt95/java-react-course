package com.bank.service;

import com.bank.model.Customer;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomer();
    void createCustomer(Customer customer);
    void updateCustomer(String id, Customer customer);
    void deleteCustomer(String id);
    Customer findCustomerById(String id);
}
