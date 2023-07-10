package com.gmail.maxkhrebtov.lucky;

import com.gmail.maxkhrebtov.order.*;
import com.gmail.maxkhrebtov.order.Package;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import static com.gmail.maxkhrebtov.order.Package.PackageType.*;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class OrderProcessorTest {

    private OrderProcessor orderProcessor;
    private final Customer customer = new Customer("John", "Doe", "johndoe@gmail.com");

    @Mock
    private OrderService orderService;

    @Test
    public void testOrderIsLucky() {
        lenient().when(orderService.findPrevious(customer))
                .thenReturn(new OrderImpl(
                        UUID.randomUUID(),
                        List.of(),
                        ZonedDateTime.now().minusHours(2))
                );

        orderProcessor = new LuckyOrderProcessorImpl(new OrderProcessorImpl(), orderService, 1.00);

        Order order = new OrderImpl(
                UUID.randomUUID(),
                List.of(
                        new Package(PANORAMA, 7.0, 1),
                        new Package(PRINT, 5.0, 1),
                        new Package(STRIP, 5.0, 1)),
                ZonedDateTime.now());

        orderProcessor.process(customer, order);

        Assertions.assertEquals(3, order.getPackages().size());

        Assertions.assertEquals(1, order.getPackages().get(0).quantity());
        Assertions.assertEquals(PANORAMA, order.getPackages().get(0).type());

        Assertions.assertEquals(1, order.getPackages().get(1).quantity());
        Assertions.assertEquals(0.0, order.getPackages().get(1).price());
        Assertions.assertEquals(PRINT, order.getPackages().get(1).type());

        Assertions.assertEquals(1, order.getPackages().get(2).quantity());
        Assertions.assertEquals(0.0, order.getPackages().get(2).price());
        Assertions.assertEquals(STRIP, order.getPackages().get(2).type());
    }

    @Test
    public void testOrderIsNotLuckyByProbability() {
        lenient().when(orderService.findPrevious(customer))
                .thenReturn(new OrderImpl(
                        UUID.randomUUID(),
                        List.of(),
                        ZonedDateTime.now().minusHours(2))
                );

        Order order = new OrderImpl(
                UUID.randomUUID(),
                List.of(
                        new Package(PANORAMA, 7.0, 1),
                        new Package(PRINT, 5.0, 1),
                        new Package(STRIP, 5.0, 1)),
                ZonedDateTime.now());
        orderProcessor = new LuckyOrderProcessorImpl(new OrderProcessorImpl(), orderService, 0.0);
        orderProcessor.process(customer, order);

        Assertions.assertEquals(3, order.getPackages().size());

        Assertions.assertEquals(1, order.getPackages().get(0).quantity());
        Assertions.assertEquals(PANORAMA, order.getPackages().get(0).type());

        Assertions.assertEquals(1, order.getPackages().get(1).quantity());
        Assertions.assertEquals(5.0, order.getPackages().get(1).price());
        Assertions.assertEquals(PRINT, order.getPackages().get(1).type());

        Assertions.assertEquals(1, order.getPackages().get(2).quantity());
        Assertions.assertEquals(5.0, order.getPackages().get(2).price());
        Assertions.assertEquals(STRIP, order.getPackages().get(2).type());
    }

    @Test
    public void testOrderIsNotLuckyByOrderPackage() {
        lenient().when(orderService.findPrevious(customer))
                .thenReturn(new OrderImpl(
                        UUID.randomUUID(),
                        List.of(),
                        ZonedDateTime.now().minusHours(2))
                );

        orderProcessor = new LuckyOrderProcessorImpl(new OrderProcessorImpl(), orderService, 1.00);

        Order order = new OrderImpl(
                UUID.randomUUID(),
                List.of(
                        new Package(PANORAMA, 7.0, 1),
                        new Package(STRIP, 5.0, 1)),
                ZonedDateTime.now());

        orderProcessor.process(customer, order);
        Assertions.assertEquals(2, order.getPackages().size());

        Assertions.assertEquals(1, order.getPackages().get(0).quantity());
        Assertions.assertEquals(PANORAMA, order.getPackages().get(0).type());

        Assertions.assertEquals(1, order.getPackages().get(1).quantity());
        Assertions.assertEquals(5.0, order.getPackages().get(1).price());
        Assertions.assertEquals(STRIP, order.getPackages().get(1).type());
    }

    @Test
    public void testOrderIsNotLuckyByTime() {
        lenient().when(orderService.findPrevious(customer))
                .thenReturn(new OrderImpl(
                        UUID.randomUUID(),
                        List.of(),
                        ZonedDateTime.now().minusMinutes(25))
                );

        Order order = new OrderImpl(
                UUID.randomUUID(),
                List.of(
                        new Package(PANORAMA, 7.0, 1),
                        new Package(PRINT, 5.0, 1),
                        new Package(STRIP, 5.0, 1)),
                ZonedDateTime.now());
        orderProcessor = new LuckyOrderProcessorImpl(new OrderProcessorImpl(), orderService, 1.0);
        orderProcessor.process(customer, order);

        Assertions.assertEquals(3, order.getPackages().size());

        Assertions.assertEquals(1, order.getPackages().get(0).quantity());
        Assertions.assertEquals(PANORAMA, order.getPackages().get(0).type());

        Assertions.assertEquals(1, order.getPackages().get(1).quantity());
        Assertions.assertEquals(5.0, order.getPackages().get(1).price());
        Assertions.assertEquals(PRINT, order.getPackages().get(1).type());

        Assertions.assertEquals(1, order.getPackages().get(2).quantity());
        Assertions.assertEquals(5.0, order.getPackages().get(2).price());
        Assertions.assertEquals(STRIP, order.getPackages().get(2).type());
    }

}
