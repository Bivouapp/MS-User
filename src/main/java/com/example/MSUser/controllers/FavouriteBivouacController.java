package com.example.MSUser.controllers;

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
    public FavouriteBivouacs createFavouriteBivouac(@RequestParam Long userId, @RequestParam Long bivouacId) {
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

        return favouriteBivouacRepository.findAllByUser_User_id(userId);
    }


    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFavouriteBivouac(@RequestParam Long userId, @RequestParam Long bivouacId) {
        FavouriteBivouacId favouriteBivouacId = new FavouriteBivouacId(userId, bivouacId);

        FavouriteBivouacs favouriteBivouac = favouriteBivouacRepository.findById(favouriteBivouacId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Favourite not found"));

        favouriteBivouacRepository.delete(favouriteBivouac);
    }
}
