package org.etraveli;

import org.etraveli.model.Customer;
import org.etraveli.model.MovieRental;
import org.etraveli.repository.MovieRepository;
import org.etraveli.repository.InMemoryMovieRepository;
import org.etraveli.service.RentalInfo;
import org.etraveli.service.RentalStatement;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";

        Customer customer = Customer.builder()
                .name("C. U. Stomer")
                .rentals(createMovieRentals())
                .build();

        MovieRepository movieRepository = new InMemoryMovieRepository();
        RentalStatement rentalStatement = new RentalInfo(movieRepository);

        String result = rentalStatement.statement(customer);

        if (!result.equals(expected)) {
            throw new AssertionError("Expected: " + System.lineSeparator() + String.format(expected) + System.lineSeparator() + System.lineSeparator() + "Got: " + System.lineSeparator() + result);
        }

        System.out.println("Success - Movie rental cost: \n" + result);
    }

    private static List<MovieRental> createMovieRentals() {
        return Arrays.asList(
                MovieRental.builder().movieId("F001").days(3).build(),
                MovieRental.builder().movieId("F002").days(1).build()
        );
    }
}