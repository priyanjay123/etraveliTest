package org.etraveli.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MovieRental {
    private String movieId;
    private int days;
}
