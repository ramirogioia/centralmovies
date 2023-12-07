/*package com.central.movies.centralmovies.dto;

import java.security.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "MoviesPlatforms")
public class MoviePlatformEntity {

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Long movie;

    @ManyToOne
    @JoinColumn(name = "platform_id")
    private Long platform;

    @Column(name = "registered_date")
    private Timestamp registeredDate;
    
}
*/