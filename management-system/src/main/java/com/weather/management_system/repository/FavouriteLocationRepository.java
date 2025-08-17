package com.weather.management_system.repository;

import com.weather.management_system.model.FavouriteLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FavouriteLocationRepository extends JpaRepository<FavouriteLocation, Long> {
    List<FavouriteLocation> findByUsernameAndActive(String username, boolean active);

    Optional<FavouriteLocation> findByUsernameAndCityAndActive(String username, String city, boolean active);

    @Query("SELECT f FROM FavouriteLocation f WHERE f.username = :username AND f.active = true ORDER BY f.addedAt DESC")
    List<FavouriteLocation> findActiveFavoritesByUser(@Param("username") String username);

    boolean existsByUsernameAndCityAndActive(String username, String city, boolean active);
}
