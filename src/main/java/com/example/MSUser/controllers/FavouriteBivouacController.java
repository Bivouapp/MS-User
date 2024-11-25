package com.example.MSUser.controllers;

import com.example.MSUser.dtos.FavouriteRequest;
import com.example.MSUser.models.FavouriteBivouacId;
import com.example.MSUser.models.FavouriteBivouacs;
import com.example.MSUser.models.User;
import com.example.MSUser.repositories.FavouriteBivouacRepository;
import com.example.MSUser.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/favourites")
public class FavouriteBivouacController {

    @Autowired
    private FavouriteBivouacRepository favouriteBivouacRepository;

    @Autowired
    private UserRepository userRepository;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FavouriteBivouacs createFavouriteBivouac(@RequestBody FavouriteRequest request) {
        Long userId = request.getUserId();
        Long bivouacId = request.getBivouacId();

        // Récupérer l'utilisateur
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        // Crée un nouveau favori
        FavouriteBivouacId favouriteBivouacId = new FavouriteBivouacId(userId, bivouacId);
        FavouriteBivouacs favouriteBivouac = new FavouriteBivouacs(favouriteBivouacId, user);

        return favouriteBivouacRepository.save(favouriteBivouac);
    }



    @GetMapping("/{userId}")
    public List<FavouriteBivouacs> getAllFavouritesByUserId(@PathVariable Long userId) {
        // Vérifie si l'utilisateur existe
        if (!userRepository.existsById(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        return favouriteBivouacRepository.findAllByUserId(userId);
    }


    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFavouriteBivouac(@RequestBody FavouriteRequest request) {
        FavouriteBivouacId favouriteBivouacId = new FavouriteBivouacId(request.getUserId(), request.getBivouacId());

        FavouriteBivouacs favouriteBivouac = favouriteBivouacRepository.findById(favouriteBivouacId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Favourite not found"));

        favouriteBivouacRepository.delete(favouriteBivouac);
    }

}
