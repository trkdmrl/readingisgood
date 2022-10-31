package com.trkdmrl.readingisgood.utils;

import com.trkdmrl.readingisgood.model.Customer;
import com.trkdmrl.readingisgood.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        Customer customer = null;
        customer = customerRepository.findCustomerByEmail(email);
        if(customer != null) {
            return false;
        }
        return true;
    }
}
