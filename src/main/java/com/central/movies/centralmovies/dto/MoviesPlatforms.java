package com.central.movies.centralmovies.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
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
@Entity(name = "movies_platforms")
public class MoviesPlatforms implements Serializable {

    @EmbeddedId
    private MoviesPlatformId id;

    @ManyToOne
    @JoinColumn(name = "movie_id", insertable = false, updatable = false)
    private MovieDTO movie;

    @ManyToOne
    @JoinColumn(name = "platform_id", insertable = false, updatable = false)
    private Platforms platform;

    @Column(name = "registered_date")
    private Timestamp registeredDate;
}
