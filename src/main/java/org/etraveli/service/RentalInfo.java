package org.etraveli.service;

import org.etraveli.model.Customer;
import org.etraveli.model.Movie;
import org.etraveli.model.MovieRental;
import org.etraveli.repository.MovieRepository;

public class RentalInfo implements RentalStatement {

    private MovieRepository movieRepository;

    public RentalInfo(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public String statement(Customer customer) {
        double totalAmount = 0;
        int frequentEnterPoints = 0;
        StringBuilder result = new StringBuilder("Rental Record for " + customer.getName() + "\n");

        for (MovieRental rental : customer.getRentals()) {
            double thisAmount = calculateAmount(rental);
            frequentEnterPoints += calculateFrequentPoints(rental);
            result.append(generateRentalLine(rental, thisAmount));
            totalAmount += thisAmount;
        }

        result.append(generateFooterLine(totalAmount, frequentEnterPoints));

        return result.toString();
    }

    private double calculateAmount(MovieRental rental) {
        Movie movie = movieRepository.getMovieById(rental.getMovieId());

        if (movie.getCode().equals("regular")) {
            return calculateRegularAmount(rental.getDays());
        } else if (movie.getCode().equals("new")) {
            return calculateNewReleaseAmount(rental.getDays());
        } else if (movie.getCode().equals("childrens")) {
            return calculateChildrensAmount(rental.getDays());
        }

        return 0;
    }

    private double calculateRegularAmount(int days) {
        double amount = 2;
        if (days > 2) {
            amount += (days - 2) * 1.5;
        }
        return amount;
    }

    private double calculateNewReleaseAmount(int days) {
        return days * 3;
    }

    private double calculateChildrensAmount(int days) {
        double amount = 1.5;
        if (days > 3) {
            amount += (days - 3) * 1.5;
        }
        return amount;
    }

    private int calculateFrequentPoints(MovieRental rental) {
        Movie movie = movieRepository.getMovieById(rental.getMovieId());

        int points = 1;
        if (movie.getCode().equals("new") && rental.getDays() > 2) {
            points++;
        }

        return points;
    }

    private String generateRentalLine(MovieRental rental, double amount) {
        Movie movie = movieRepository.getMovieById(rental.getMovieId());
        return "\t" + movie.getTitle() + "\t" + amount + "\n";
    }

    private String generateFooterLine(double totalAmount, int frequentEnterPoints) {
        return "Amount owed is " + totalAmount + "\n" +
                "You earned " + frequentEnterPoints + " frequent points\n";
    }
}