package com.gmail.maxkhrebtov.taxes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Month;

@Getter
@AllArgsConstructor
@Builder
public class MonthTaxReport {
    private Month month;
    private double totalRevenue;
    private double tax;
    private double taxRate;
}
