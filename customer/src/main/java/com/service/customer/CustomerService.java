package com.service.customer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    public void registerCustomer(CustomerRegistrationRequest request){
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        //todo: check if email valid
        //todo: check if email not taken
        //todo: store customer in db
        customerRepository.saveAndFlush(customer);
        //todo: check if fraudster
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://FRAUD/api/services/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getEmail()
        );

        if (fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("Fraudster found");
        }

    }
}
