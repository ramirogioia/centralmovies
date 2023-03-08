package com.central.movies.centralmovies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.central.movies.centralmovies.dto.SearchCacheEntity;

public interface SearchCacheRepository extends JpaRepository<SearchCacheEntity, Long> {

    SearchCacheEntity findByQuery(String query);

}