package com.botscrew.facebook.model.payment;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Payment {

    private String currency = "USD";
    @JsonProperty("payment_type")
    private String paymentType = "FIXED_AMOUNT";
    @JsonProperty("is_test_payment")
    private Boolean isTestPayment;
    @JsonProperty("merchant_name")
    private String merchantName = "Nikhil Nirmel";
    @JsonProperty("requested_user_info")
    private List<String> requestedUserInfo = Arrays.asList("contact_phone");
    @JsonProperty("price_list")
    private List<PriceObject> priceList = new ArrayList<>();

    public Payment() {
    }

    public Payment(List<PriceObject> priceList) {
        this.priceList = priceList;
    }

    public static Payment createPayment(List<PriceObject> priceList) {
        Payment payment = new Payment(priceList);
        return payment;
    }

    public static Payment createTestPayment(List<PriceObject> priceList) {
        Payment payment = new Payment(priceList);
        payment.setTestPayment(true);
        return payment;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<PriceObject> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<PriceObject> priceList) {
        this.priceList = priceList;
    }

    public List<String> getRequestedUserInfo() {
        return requestedUserInfo;
    }

    public void setRequestedUserInfo(List<String> requestedUserInfo) {
        this.requestedUserInfo = requestedUserInfo;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public void setTestPayment(Boolean testPayment) {
        isTestPayment = testPayment;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}
