package com.central.movies.centralmovies.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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
@Entity(name = "platforms")
public class Platforms {

    @Id
    private Long platform;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private boolean isOnline;
    
}
