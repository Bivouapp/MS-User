package com.example.MSUser.controllers;

import com.example.MSUser.dtos.LoginRequest;
import com.example.MSUser.dtos.LoginResponse;
import com.example.MSUser.dtos.RegisterRequest;
import com.example.MSUser.dtos.UpdateUserRequest;
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
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        Optional<User> user = userService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        if (user.isPresent()) {
            String token = jwtUtils.generateToken(user.get());
            return new LoginResponse(token, user.get().getUser_id());
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
    }



    @GetMapping
    public List<User> list () {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @GetMapping("email/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userRepository.findByEmail(email).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @GetMapping("/hosts")
    public List<User> getHosts() {
        return userRepository.findByIs_hostTrue();
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody UpdateUserRequest updateRequest) {
        // Vérifier si l'utilisateur existe
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        // Récupérer l'utilisateur existant
        User user = optionalUser.get();

        // Mettre à jour les champs modifiables
        if (updateRequest.getFirstName() != null) {
            user.setFirst_name(updateRequest.getFirstName());
        }
        if (updateRequest.getLastName() != null) {
            user.setLast_name(updateRequest.getLastName());
        }
        if (updateRequest.getPhoneNumber() != null) {
            user.setPhone_number(updateRequest.getPhoneNumber());
        }
        if (updateRequest.getIsHost() != null) {
            user.setIs_host(updateRequest.getIsHost());
        }

        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID " + id + " not found");
        }
        userRepository.deleteById(id);
    }





}
