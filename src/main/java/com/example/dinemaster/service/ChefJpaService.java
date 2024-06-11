package com.example.dinemaster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

import com.example.dinemaster.model.Restaurant;
import com.example.dinemaster.model.Chef;
import com.example.dinemaster.repository.ChefRepository;
import com.example.dinemaster.repository.ChefJpaRepository;
import com.example.dinemaster.repository.RestaurantJpaRepository;

@Service
public class ChefJpaService implements ChefRepository {

    @Autowired
    private ChefJpaRepository db;

    @Autowired
    private RestaurantJpaRepository db1;

    @Override
    public ArrayList<Chef> getChefs() {
        return (ArrayList<Chef>) db.findAll();
    }

    @Override
    public Chef getChefById(int Id) {
        try {
            Chef chef = db.findById(Id).get();
            return chef;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Chef addChef(Chef chef) {
        Restaurant restaurant = chef.getRestaurant();
        int restaurantId = restaurant.getId();
        try {
            restaurant = db1.findById(restaurantId).get();
            chef.setRestaurant(restaurant);
            db.save(chef);

            return chef;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Chef updateChef(int id, Chef chef) {
        try {
            Chef newOne = db.findById(id).get();
            if (chef.getFirstName() != null) {
                newOne.setFirstName(chef.getFirstName());
            }
            if (chef.getLastName() != null) {
                newOne.setLastName(chef.getLastName());
            }
            if (chef.getExpertise() != null) {
                newOne.setExpertise(chef.getExpertise());
            }
            if (chef.getExperienceYears() != 0) {
                newOne.setExperienceYears(chef.getExperienceYears());
            }
            if (chef.getRestaurant() != null) {
                int restaurantId = chef.getRestaurant().getId();
                Restaurant newRestaurant = db1.findById(restaurantId).get();
                newOne.setRestaurant(newRestaurant);
            }

            db.save(newOne);

            return newOne;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Chef deleteChef(int id){
        try {
            db.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @Override
    public Restaurant getChefRestaurant(int id) {
        try {
            Chef chef = db.findById(id).get();
            Restaurant restaurant = chef.getRestaurant();
            return restaurant;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
