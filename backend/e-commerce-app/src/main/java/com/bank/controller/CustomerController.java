package com.bank.controller;

import com.bank.model.Customer;
import com.bank.service.CustomerService;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Customer> getAllCustomer() {
        return customerService.getAllCustomer();
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public String createCategory(@RequestBody Customer customer) {
        customerService.createCustomer(customer);
        return "ok";
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCategory(@PathVariable("id") String id,
                                            @RequestBody Customer customer) {
        customerService.updateCustomer(id, customer);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> createCategory(@PathVariable("id") String id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findCustomerById(@PathVariable("id") String id) {
        return new ResponseEntity<>(customerService.findCustomerById(id), HttpStatus.OK);
    }
}
