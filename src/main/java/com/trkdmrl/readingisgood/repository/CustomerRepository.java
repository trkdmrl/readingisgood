package com.trkdmrl.readingisgood.repository;

import com.trkdmrl.readingisgood.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findCustomerByEmail(String email);

    Customer findByUsername(String username);
}
