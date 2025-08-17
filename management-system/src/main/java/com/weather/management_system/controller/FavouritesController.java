package com.weather.management_system.controller;

import com.weather.management_system.model.FavouriteLocation;
import com.weather.management_system.service.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/weather/favourites")
@CrossOrigin(origins = "http://localhost:5173")
public class FavouritesController {
    @Autowired
    private FavouriteService favouriteService;

    @GetMapping
    public ResponseEntity<List<FavouriteLocation>> getFavourites() {
        return ResponseEntity.ok(favouriteService.getAllFavouritesForCurrentUser());
    }

    @PostMapping
    public ResponseEntity<?> addFavourite(@RequestParam("city") String city) {
        try {
            return ResponseEntity.ok(favouriteService.addFavourite(city));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @DeleteMapping("/{city}")
    public ResponseEntity<?> removeFavourite(@PathVariable String city) {
        try {
            favouriteService.removeFavourite(city);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
