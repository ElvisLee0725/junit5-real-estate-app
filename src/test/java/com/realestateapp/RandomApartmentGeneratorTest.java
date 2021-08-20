package com.realestateapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomApartmentGeneratorTest {
    private static final double MAX_MULTIPLIER = 4.0;

    @Nested
    class GenerateDefaultParamsTest {
        RandomApartmentGenerator randomApartmentGenerator;
        @BeforeEach
        void setup() {
            this.randomApartmentGenerator = new RandomApartmentGenerator();
        }

        // Test 10 times and make sure each new Apartment's area and price is within min and max range
        @RepeatedTest(value = 10)
        void should_GenerateCorrectApartment_When_DefaultMinAreaMinPrice() {
            double minArea = 30.0;
            double maxArea = minArea * MAX_MULTIPLIER;
            BigDecimal minPricePerSquareMeter = new BigDecimal(3000.0);
            BigDecimal maxPricePerSquareMeter = minPricePerSquareMeter.multiply(new BigDecimal(MAX_MULTIPLIER));

            Apartment apartment = randomApartmentGenerator.generate();
            BigDecimal minApartmentPrice = new BigDecimal(apartment.getArea()).multiply(minPricePerSquareMeter);
            BigDecimal maxApartmentPrice = new BigDecimal(apartment.getArea()).multiply(maxPricePerSquareMeter);

            assertAll(
                    () -> assertTrue(apartment.getArea() >= minArea),
                    () -> assertTrue(apartment.getArea() <= maxArea),
                    () -> assertTrue(apartment.getPrice().compareTo(minApartmentPrice) >= 0),
                    () -> assertTrue(apartment.getPrice().compareTo(maxApartmentPrice) <= 0)
            );
        }
    }

    @Nested
    class GenerateCustomParamsTest {
        RandomApartmentGenerator randomApartmentGenerator;
        private double minArea = 15.0;
        private BigDecimal minPricePerSquareMeter = new BigDecimal(5000.0);

        @BeforeEach
        void setup() {
            randomApartmentGenerator = new RandomApartmentGenerator(minArea, minPricePerSquareMeter);
        }

        @RepeatedTest(value = 10)
        void should_GenerateCorrectApartment_When_CustomMinAreaMinPrice() {
            double minArea = this.minArea;
            double maxArea = minArea * MAX_MULTIPLIER;
            BigDecimal minPricePerSquareMeter = this.minPricePerSquareMeter;
            BigDecimal maxPricePerSquareMeter = minPricePerSquareMeter.multiply(new BigDecimal(MAX_MULTIPLIER));

            Apartment apartment = randomApartmentGenerator.generate();

            BigDecimal minApartmentPrice = minPricePerSquareMeter.multiply(new BigDecimal(apartment.getArea()));
            BigDecimal maxApartmentPrice = maxPricePerSquareMeter.multiply(new BigDecimal(apartment.getArea()));

            assertAll(
                    () -> assertTrue(apartment.getArea() >= minArea),
                    () -> assertTrue(apartment.getArea() <= maxArea),
                    () -> assertTrue(apartment.getPrice().compareTo(minApartmentPrice) >= 0),
                    () -> assertTrue(apartment.getPrice().compareTo(maxApartmentPrice) <= 0)
            );
        }
    }

}