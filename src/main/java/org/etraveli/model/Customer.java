package org.etraveli.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class Customer {
    private String name;
    private List<MovieRental> rentals;
}
