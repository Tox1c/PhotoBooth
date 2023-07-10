package com.gmail.maxkhrebtov.order;

public interface OrderProcessor {
    void process(Customer customer, Order order);
}
