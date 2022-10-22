package com.service.fraud;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/services/fraud-check")
@AllArgsConstructor
public class FraudController {
    private final FraudCheckService fraudCheckService;


    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraudster(
        @PathVariable("customerId") Integer customerID){
    boolean isFraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerID);
    return new FraudCheckResponse(isFraudulentCustomer);

    }
}
