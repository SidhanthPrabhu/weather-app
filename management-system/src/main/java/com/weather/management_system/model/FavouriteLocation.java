package com.weather.management_system.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "favourite_locations")
@Getter
@Setter
public class FavouriteLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private boolean active = true;

    @Column(nullable = false)
    private LocalDateTime addedAt = LocalDateTime.now();

    // Add constructor for easier creation
    public FavouriteLocation() {}

    public FavouriteLocation(String city, String username) {
        this.city = city;
        this.username = username;
    }
}