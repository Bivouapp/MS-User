package com.example.demo.controllers;

import com.example.demo.models.Location;
import com.example.demo.models.User;
import com.example.demo.repositories.LocationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/locations")
public class LocationsController {

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping
    public List<Location> list () {
        return locationRepository.findAll();
    }
    @GetMapping
    @RequestMapping("{id}")
    public Location get(@PathVariable Long id) {
        if (locationRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location with ID "+id+" not found");
        }
        return locationRepository.getById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Location create(@RequestBody final Location location) {
        return locationRepository.saveAndFlush(location);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        // Toujours verifier s’il faut
        // les enregistrements enfants
        locationRepository.deleteById(id);
    }

    @RequestMapping(value="{id}",method = RequestMethod.PUT)
    public Location update(@PathVariable Long id, @RequestBody User user) {
        // TO DO: Ajouter ici une validation si tous
        // les champs ont ete passes
        // Sinon , retourner une erreur 400 (Bad Payload )
        Location existingUser = locationRepository.getById(id);
        BeanUtils.copyProperties(user,existingUser ,"user_id");
        return locationRepository.saveAndFlush(existingUser);
    }

}
