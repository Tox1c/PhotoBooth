package com.gmail.maxkhrebtov.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import static com.gmail.maxkhrebtov.order.Package.PackageType.*;

@Getter
@AllArgsConstructor
public class OrderImpl implements Order {

    private UUID id;
    @Setter
    private List<Package> packages;
    private ZonedDateTime createdTs;

    public double getTotalPrice() {
        return packages.stream()
                .mapToDouble(Package::getTotalPrice)
                .sum();
    }

    @Override
    public boolean isEligibleForLottery() {
        return new HashSet<>(packages.stream().map(Package::type).toList()).containsAll(List.of(PRINT, PANORAMA, STRIP));
    }
}
