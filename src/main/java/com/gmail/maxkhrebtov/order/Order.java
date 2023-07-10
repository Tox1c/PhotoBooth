package com.gmail.maxkhrebtov.order;

import com.gmail.maxkhrebtov.lucky.LotteryEligible;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public interface Order extends LotteryEligible {
    UUID getId();

    double getTotalPrice();
    List<Package> getPackages();

    void setPackages(List<Package> packages);

    ZonedDateTime getCreatedTs();
}
