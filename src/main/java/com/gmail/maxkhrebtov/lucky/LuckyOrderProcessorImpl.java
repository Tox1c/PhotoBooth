package com.gmail.maxkhrebtov.lucky;

import com.gmail.maxkhrebtov.order.*;
import com.gmail.maxkhrebtov.order.Package;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

@RequiredArgsConstructor
public class LuckyOrderProcessorImpl implements OrderProcessor {

    private final OrderProcessor delegateOrderProcessor;
    private final OrderService orderService;

    private static final double defaultProbability = 0.05;
    private static final int luckinessEligibilityTimeGapHours = 1;
    private final Double probability;

    @Override
    public void process(Customer customer, Order order) {
        processUpdateOrderIfLuckyCase(customer, order);
        delegateOrderProcessor.process(customer, order);
    }

    private void processUpdateOrderIfLuckyCase(Customer customer, Order order) {
        if (order.isEligibleForLottery() && isLuckyTime(customer, order)) {
            //1. check price of each package and take the most expensive
            order.getPackages().stream().max(Comparator.comparingDouble(Package::getTotalPrice))
                    .ifPresent(expensivePackage -> {
                        ArrayList<Package> newPackages = new ArrayList<>();
                        newPackages.add(expensivePackage);
                        //2. make 2 others for free
                        newPackages.addAll(order.getPackages().stream()
                                .filter(p -> p.type() != expensivePackage.type())
                                .map(p -> new Package(p.type(), 0.0, p.quantity()))
                                .toList());
                        order.setPackages(newPackages);
                    });
        }
    }

    private boolean isLuckyTime(Customer customer, Order order) {
        return Math.random() < getProbability()
                && isEligibleTime(customer, order);
    }

    private boolean isEligibleTime(Customer customer, Order order) {
        return Optional.ofNullable(orderService.findPrevious(customer))
                .map(prevOrder -> prevOrder.getCreatedTs().plusHours(luckinessEligibilityTimeGapHours).isBefore(order.getCreatedTs()))
                .orElse(false);
    }

    private double getProbability() {
        return probability != null ? probability : defaultProbability;
    }
}
