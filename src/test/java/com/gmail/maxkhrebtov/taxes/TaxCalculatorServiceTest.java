package com.gmail.maxkhrebtov.taxes;

import com.gmail.maxkhrebtov.order.Order;
import com.gmail.maxkhrebtov.order.OrderImpl;
import com.gmail.maxkhrebtov.order.Package;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaxCalculatorServiceTest {

    private final TaxCalculatorService taxCalculatorService = new TaxCalculatorService();

    @Test
    public void testCalculateTaxesByMonths() {
        List<Order> orders = new ArrayList<>();
        orders.add(new OrderImpl(UUID.randomUUID(), createPackages(100.0, 200.0, 150.0), ZonedDateTime.of(LocalDateTime.of(2022, Month.JANUARY, 5, 12, 0), ZoneId.systemDefault())));
        orders.add(new OrderImpl(UUID.randomUUID(), createPackages(300.0, 250.0), ZonedDateTime.of(LocalDateTime.of(2022, Month.FEBRUARY, 10, 9, 30), ZoneId.systemDefault())));
        orders.add(new OrderImpl(UUID.randomUUID(), createPackages(180.0, 220.0, 200.0), ZonedDateTime.of(LocalDateTime.of(2022, Month.MARCH, 2, 16, 15), ZoneId.systemDefault())));
        orders.add(new OrderImpl(UUID.randomUUID(), createPackages(150.0, 120.0), ZonedDateTime.of(LocalDateTime.of(2022, Month.APRIL, 20, 11, 45), ZoneId.systemDefault())));
        orders.add(new OrderImpl(UUID.randomUUID(), createPackages(150.0, 120.0), ZonedDateTime.of(LocalDateTime.of(2022, Month.DECEMBER, 20, 11, 45), ZoneId.systemDefault())));

        var report = taxCalculatorService.calculateTaxesByMonths(orders);
        Assertions.assertNotNull(report);
        Assertions.assertEquals(5, report.monthTaxReports().size());
        Assertions.assertEquals(2140.0, report.monthTaxReports().stream().mapToDouble(MonthTaxReport::getTotalRevenue).sum());
    }

    private List<Package> createPackages(double... prices) {
        List<Package> packages = new ArrayList<>();
        for (double price : prices) {
            packages.add(new Package(Package.PackageType.PRINT, price, 1));
        }
        return packages;
    }
}
