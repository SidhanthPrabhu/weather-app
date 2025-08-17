package com.weather.management_system.service;

import com.weather.management_system.model.FavouriteLocation;
import com.weather.management_system.repository.FavouriteLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FavouriteService {

    private final FavouriteLocationRepository repository;

    @Autowired
    public FavouriteService(FavouriteLocationRepository repository) {
        this.repository = repository;
    }

    public List<FavouriteLocation> getAllFavouritesForCurrentUser() {
        String username = getCurrentUsername();
        return repository.findByUsernameAndActive(username, true);
    }

    public FavouriteLocation addFavourite(String city) {
        String username = getCurrentUsername();

        Optional<FavouriteLocation> existing = repository.findByUsernameAndCityAndActive(
                username, city, true);

        if (existing.isPresent()) {
            throw new IllegalStateException("City already in favourites");
        }

        FavouriteLocation location = new FavouriteLocation();
        location.setCity(city);
        location.setUsername(username);
        location.setActive(true);
        return repository.save(location);
    }

    public void removeFavourite(String city) {
        String username = getCurrentUsername();

        Optional<FavouriteLocation> existing = repository.findByUsernameAndCityAndActive(
                username, city, true);

        if (existing.isEmpty()) {
            throw new IllegalArgumentException("Favourite not found");
        }
        FavouriteLocation fav = existing.get();
        fav.setActive(false);
        repository.save(fav);
    }

    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("User not authenticated");
        }
        return authentication.getName();
    }
}