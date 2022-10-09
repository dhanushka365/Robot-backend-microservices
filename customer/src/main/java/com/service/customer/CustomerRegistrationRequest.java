package com.service.customer;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email) {
}
