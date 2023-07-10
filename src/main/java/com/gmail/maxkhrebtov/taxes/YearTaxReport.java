package com.gmail.maxkhrebtov.taxes;

import java.util.Set;

public record YearTaxReport(Set<MonthTaxReport> monthTaxReports) {
}
