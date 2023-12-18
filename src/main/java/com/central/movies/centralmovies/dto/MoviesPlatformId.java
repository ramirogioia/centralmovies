package com.central.movies.centralmovies.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MoviesPlatformId implements Serializable {

    @Column(name = "movie_id")
    private Long movie;

    @Column(name = "platform_id")
    private Long platform;
}