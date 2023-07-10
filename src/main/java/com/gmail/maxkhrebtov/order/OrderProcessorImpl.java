package com.gmail.maxkhrebtov.order;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderProcessorImpl implements OrderProcessor {

    @Override
    public void process(Customer customer, Order order) {
        //some business logic to process the order
    }
}
