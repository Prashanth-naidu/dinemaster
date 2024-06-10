package com.example.dinemaster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

import com.example.dinemaster.model.Chef;
import com.example.dinemaster.model.Restaurant;
import com.example.dinemaster.service.ChefJpaService;

@RestController
public class ChefController {

    @Autowired
    private ChefJpaService s;

    @GetMapping("/restaurants/chefs")
    public ArrayList<Chef> getChefs() {
        return s.getChefs();
    }

    @GetMapping("/restaurants/chefs/{chefId}")
    public Chef getChefById(@PathVariable("id") int id) {
        return s.getChefById(id);
    }

    @PostMapping("/restaurants/chefs")
    public Chef addChef(@RequestBody Chef chef) {
        return s.addChef(chef);
    }

    @PutMapping("/restaurants/chefs/{chefId}")
    public Chef updateChef(@PathVariable("id") int id, @RequestBody Chef chef) {
        return s.updateChef(id, chef);
    }

    @DeleteMapping("/restaurants/chefs/{chefId}")
    public void deleteChef(@PathVariable int id) {
        s.deleteChef(id);
    }

    @GetMapping("/chefs/{chefId}/restaurant")
    public Restaurant getChefRestaurant(@PathVariable int id) {
        return s.getChefRestaurant(id);
    }
}
