package com.codeWithProjects.fitnessTrackerServer.service;
import com.codeWithProjects.fitnessTrackerServer.dto.BookingDTO;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentService {
    public String processPayment(BookingDTO dto) {
        try {
            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("amount", 1000); // $10.00
            chargeParams.put("currency", "usd");
            chargeParams.put("source", "tok_visa"); // Test token
            Charge.create(chargeParams);
            return "PAID";
        } catch (StripeException e) {
            return "FAILED";
        }
    }
}