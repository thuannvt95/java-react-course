package com.bank.service;

import com.bank.model.Customer;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    Page<Customer> getAllCustomer(Pageable pageable);
    void createCustomer(Customer customer);
    void updateCustomer(String id, Customer customer);
    void deleteCustomer(String id);
    Customer findCustomerById(String id);
}
