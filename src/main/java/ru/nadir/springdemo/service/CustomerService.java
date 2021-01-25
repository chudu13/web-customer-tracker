package ru.nadir.springdemo.service;

import ru.nadir.springdemo.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getCustomers();

    Customer getCustomer(int theId)
            ;
    void saveCustomer(Customer customer);

    void deleteCustomer(int theId);
}
