package com.mallmanagement.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repo;

    public List<Customer> listAll() {
        return repo.findAll();
    }

    public Customer save(Customer customer) {
        if (customer.getMemberSince() == null) {
            customer.setMemberSince(LocalDate.now());
        }
        return repo.save(customer);
    }

    public Customer get(Long id) {
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Customer not found with ID: " + id));
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new NoSuchElementException("Customer not found with ID: " + id);
        }
        repo.deleteById(id);
    }
}
