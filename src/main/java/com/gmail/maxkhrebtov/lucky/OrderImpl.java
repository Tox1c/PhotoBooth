package com.gmail.maxkhrebtov.lucky;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import static com.gmail.maxkhrebtov.lucky.Package.PackageType.*;

@Getter
@AllArgsConstructor
public class OrderImpl implements Order {

    private UUID id;
    @Setter
    private List<Package> packages;
    private ZonedDateTime createdTs;

    @Override
    public boolean isEligibleForLottery() {
        return new HashSet<>(packages.stream().map(Package::type).toList()).containsAll(List.of(PRINT, PANORAMA, STRIP));
    }
}
