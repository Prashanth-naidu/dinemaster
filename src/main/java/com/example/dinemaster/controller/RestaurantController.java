package com.example.dinemaster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

import com.example.dinemaster.model.Restaurant;
import com.example.dinemaster.service.RestaurantJpaService;
import com.example.dinemaster.service.RestaurantJpaservice;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantJpaService s;

    @GetMapping("/restaurants")
    public ArrayList<Restaurant> getRestaurants() {
        return s.getRestaurants();
    }

    @GetMapping("/restaurants/{restaurantId}")
    public Restaurant getRestaurantById(@PathVariable("id") int id) {
        return s.getRestaurantById(id);
    }

    @PostMapping("/restaurants")
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
        return s.addRestaurant(restaurant);
    }

    @PutMapping("/restaurants/{restaurantId}")
    public Restaurant updateRestaurant(@PathVariable("id") int id, @RequestBody Restaurant restaurant) {
        return s.updateRestaurant(id, restaurant);
    }

    @DeleteMapping("/restaurants/{restaurantId}")
    public void deleteRestaurant(@PathVariable("id") int id) {
        s.deleteRestaurant(id);
    }
}