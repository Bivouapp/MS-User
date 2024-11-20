package com.example.MSUser.controllers;

import com.example.MSUser.dtos.LoginRequest;
import com.example.MSUser.dtos.RegisterRequest;
import com.example.MSUser.models.User;
import com.example.MSUser.repositories.UserRepository;
import com.example.MSUser.services.UserService;
import com.example.MSUser.utils.JwtUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest registerRequest) {
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());

        return userService.register(user);
    }


    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        Optional<User> user = userService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        if (user.isPresent()) {
            return jwtUtils.generateToken(user.get());
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
    }


    @GetMapping
    public List<User> list () {
        return userRepository.findAll();
    }

    /*
    @GetMapping
    @RequestMapping("{id}")
    public User get(@PathVariable Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID "+id+" not found");
        }
        return userRepository.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody final User user) {
        return userRepository.saveAndFlush(user);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        // Toujours verifier s’il faut
        // les enregistrements enfants
        userRepository.deleteById(id);
    }

    @RequestMapping(value="{id}",method = RequestMethod.PUT)
    public User update(@PathVariable Long id, @RequestBody User user) {
        // TO DO: Ajouter ici une validation si tous
        // les champs ont ete passes
        // Sinon , retourner une erreur 400 (Bad Payload )
        User existingUser = userRepository.getById(id);
        BeanUtils.copyProperties(user,existingUser ,"user_id");
        return userRepository.saveAndFlush(existingUser);
    }*/

}
