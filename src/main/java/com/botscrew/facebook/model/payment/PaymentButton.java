package com.botscrew.facebook.model.payment;


import com.botscrew.facebook.model.Button;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentButton extends Button {

    @JsonProperty("payment_summary")
    private Payment payment;

    public PaymentButton() {
    }

    public PaymentButton(String title, String payload) {
        this.setTitle(title);
        this.setPayload(payload);
    }

    public static PaymentButton createPaymentButton(String payload, List<PriceObject> priceObjects) {
        PaymentButton button = new PaymentButton("buy", payload);
        button.setType("payment");
        button.setPayment(Payment.createPayment(priceObjects));
        return button;
    }

    public static PaymentButton createTestPaymentButton(String payload, List<PriceObject> priceObjects) {
        PaymentButton button = new PaymentButton("buy", payload);
        button.setType("payment");
        button.setPayment(Payment.createTestPayment(priceObjects));
        return button;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
