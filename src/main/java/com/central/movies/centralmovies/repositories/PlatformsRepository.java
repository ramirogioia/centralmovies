package com.central.movies.centralmovies.repositories;

import com.central.movies.centralmovies.dto.Platforms;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlatformsRepository extends JpaRepository<Platforms, Long> {

    List<Platforms> findAll();
}
