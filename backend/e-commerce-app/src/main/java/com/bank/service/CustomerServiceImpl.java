package com.bank.service;

import com.bank.Repository.CustomerRepository;
import com.bank.model.Customer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    CustomerRepository customerRepository;

    @Override
    public Page<Customer> getAllCustomer(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public void createCustomer(Customer customer) {
        try {
            customerRepository.save(customer);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public void updateCustomer(String id, Customer customer) {
        Optional<Customer> foundCustomer = customerRepository.findById(id);
        if (foundCustomer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }

        if (StringUtils.hasText(customer.getFirstName())) {
            foundCustomer.get().setFirstName(customer.getFirstName());
        }

        if (StringUtils.hasText(customer.getLastName())) {
            foundCustomer.get().setFirstName(customer.getLastName());
        }

        if (StringUtils.hasText(customer.getEmail())) {
            foundCustomer.get().setFirstName(customer.getEmail());
        }

        if (StringUtils.hasText(customer.getPhoneNumber())) {
            foundCustomer.get().setFirstName(customer.getPhoneNumber());
        }

        customerRepository.save(foundCustomer.get());
    }

    @Override
    public void deleteCustomer(String id) {
        Optional<Customer> foundCustomer = customerRepository.findById(id);
        if (foundCustomer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
        customerRepository.delete(foundCustomer.get());
    }

    @Override
    public Customer findCustomerById(String id) {
        Optional<Customer> foundCustomer = customerRepository.findById(id);
        if (foundCustomer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
        return foundCustomer.get();
    }
}
