package com.realestateapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApartmentRaterTest {

    @ParameterizedTest(name = "area={0}, price={1}, rating={2}")
    @CsvSource(value = {"20.0, 100000, 0", "30.0, 210000, 1", "40.0, 330000, 2"})
    void should_ReturnCorrectRating_When_CorrectApartment(Double area, int price, int rating) {
        Apartment apartment = new Apartment(area, new BigDecimal(price));
        int expected = rating;

        int actual = ApartmentRater.rateApartment(apartment);

        assertEquals(expected, actual);
    }

    @Test
    void should_ReturnErrorValue_When_IncorrectApartment() {
        double area = -10.0;
        int price = 200000;
        Apartment apartment = new Apartment(area, new BigDecimal(price));
        int expected = -1;

        int actual = ApartmentRater.rateApartment(apartment);

        assertEquals(expected, actual);
    }

    @Test
    void should_CalculateAverageRating_When_CorrectApartmentList() {
        List<Apartment> listOfApartments = new ArrayList<>();
        listOfApartments.add(new Apartment(20.0, new BigDecimal(100000)));
        listOfApartments.add(new Apartment(30.0, new BigDecimal(210000)));
        listOfApartments.add(new Apartment(40.0, new BigDecimal(330000)));
        double expected = 1.0;

        double actual = ApartmentRater.calculateAverageRating(listOfApartments);

        assertEquals(expected, actual);
    }

    @Test
    void should_ThrowExceptionInCalculateAverageRating_When_EmptyApartmentList() {
        List<Apartment> apartmentList = new ArrayList<>();

        Executable executable = () -> ApartmentRater.calculateAverageRating(apartmentList);

        assertThrows(RuntimeException.class, executable);
    }
}