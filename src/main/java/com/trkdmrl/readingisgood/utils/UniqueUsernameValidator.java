package com.trkdmrl.readingisgood.utils;

import com.trkdmrl.readingisgood.model.Customer;
import com.trkdmrl.readingisgood.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        Customer customer = customerRepository.findByUsername(username);
        if(customer == null) {
            return true;
        }
        return false;
    }
}
