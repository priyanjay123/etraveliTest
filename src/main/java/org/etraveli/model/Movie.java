package org.etraveli.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Movie {
    private String title;
    private String code;
}