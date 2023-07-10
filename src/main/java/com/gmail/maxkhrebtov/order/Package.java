package com.gmail.maxkhrebtov.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public record Package(PackageType type, double price, int quantity) {

    public double getTotalPrice() {
        return price * quantity;
    }
    @RequiredArgsConstructor
    @Getter
    public enum PackageType {
        PRINT("4x6 photo"),
        PANORAMA("6x12 prints"),
        STRIP("two 2x6 photo strips");
        private final String description;
    }
}
