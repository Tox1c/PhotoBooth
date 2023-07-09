package com.gmail.maxkhrebtov.lucky;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public interface Order extends LotteryEligible {

    UUID getId();
    double getTotalPrice();

    List<Package> getPackages();

    ZonedDateTime getCreatedTs();
}
