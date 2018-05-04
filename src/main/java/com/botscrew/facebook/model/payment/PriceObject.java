package com.botscrew.facebook.model.payment;


public class PriceObject {

    private String label;
    private double amount;

    public PriceObject() {
    }

    public PriceObject(String label, double amount) {
        this.label = label;
        this.amount = amount;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
