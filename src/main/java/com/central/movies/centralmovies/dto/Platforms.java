package com.central.movies.centralmovies.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @JsonIgnore
    @Column(name = "platform_id")
    private Long platformId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "stream_url")
    private String streamUrl;

    @Column(name = "is_online")
    private boolean isOnline;
    
}
