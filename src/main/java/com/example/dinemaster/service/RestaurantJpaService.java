package com.example.dinemaster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

import com.example.dinemaster.model.Restaurant;
import com.example.dinemaster.repository.RestaurantJpaRepository;
import com.example.dinemaster.repository.RestaurantRepository;

@Service
public class RestaurantJpaService implements RestaurantRepository {

    @Autowired
    private RestaurantJpaRepository db;

    @Override
    public ArrayList<Restaurant> getRestaurants() {
        return (ArrayList<Restaurant>) db.findAll();
    }

    @Override
    public Restaurant getRestaurantById(int restaurantId) {
        try {
            Restaurant restaurant = db.findById(restaurantId).get();
            return restaurant;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Restaurant addRestaurant(Restaurant restaurant) {
        try {
            db.save(restaurant);

            return restaurant;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Restaurant updateRestaurant(int restaurantId, Restaurant restaurant) {
        try {
            Restaurant newOne = db.findById(restaurantId).get();
            if (restaurant.getName() != null) {
                newOne.setName(restaurant.getName());
            }
            if (restaurant.getAddress() != null) {
                newOne.setAddress(restaurant.getAddress());
            }
            if (restaurant.getCuisineType() != null) {
                newOne.setCuisineType(restaurant.getCuisineType());
            }
            if (restaurant.getRating() != 0) {
                newOne.setRating(restaurant.getRating());
            }

            db.save(newOne);

            return newOne;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteRestaurant(int restaurantId) {
        try {
            db.deleteById(restaurantId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }
}
