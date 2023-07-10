package com.gmail.maxkhrebtov.taxes;

import com.gmail.maxkhrebtov.order.Order;

import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TaxCalculatorService {

    private final static double taxRate = 8.625 / 100;

    public YearTaxReport calculateTaxesByMonths(List<Order> orders) {
        return new YearTaxReport(calculateRevenueAndTaxByMonth(orders));
    }

    private Set<MonthTaxReport> calculateRevenueAndTaxByMonth(List<Order> orders) {
        return getRevenueByMonth(orders).entrySet().stream()
                .map(entry ->
                        MonthTaxReport.builder()
                                .month(entry.getKey())
                                .totalRevenue(entry.getValue())
                                .taxRate(taxRate)
                                .tax(entry.getValue() * taxRate)
                                .build()).collect(Collectors.toSet());
    }

    private Map<Month, Double> getRevenueByMonth(List<Order> orders) {
        return orders.stream()
                .collect(
                        HashMap::new,
                        (map, order) -> {
                            Month month = order.getCreatedTs().getMonth();
                            double orderRevenue = order.getTotalPrice();
                            map.put(month, map.getOrDefault(month, 0.0) + orderRevenue);
                        },
                        HashMap::putAll
                );
    }
}
