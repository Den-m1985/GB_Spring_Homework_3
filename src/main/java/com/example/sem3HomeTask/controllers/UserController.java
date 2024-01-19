package com.example.sem3HomeTask.controllers;

import com.example.sem3HomeTask.domain.User;
import com.example.sem3HomeTask.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private RegistrationService service;

    /**
     * Endpoint to get a list of all users.
     * localhost:8080/user
     *
     * @return A list containing all users.
     */
    @GetMapping
    public List<User> userList() {
        return service.getDataProcessingService().getRepository().getUsers();
    }


    /**
     * localhost:8080/user/body
     * Example for Postman:
     * {
     * "name": "John Doe",
     * "age": 20,
     * "email": "john.doe@example.com"
     * }
     * Endpoint a POST request to add a new user to the system using the provided request body.
     * localhost:8080/user/body
     *
     * @param user The User object containing the details of the user to be added.
     * @return A success message indicating that the user has been added from the request body.
     */
    @PostMapping("/body")
    public String userAddFromBody(@RequestBody User user) {
        service.processRegistration(user);
        return "User added from body!";
    }


    /**
     * Endpoint to handle a POST request to add a new user to the system using parameters in the request.
     * localhost:8080/user/param?name=John%20Doe&age=25&email=john.doe@example.com
     *
     * @param name  The name of the user.
     * @param age   The age of the user.
     * @param email The email of the user.
     * @return A success message indicating that the user has been added from parameters.
     */
    @PostMapping("/param")
    public String userAddFromParam(
            @RequestParam String name,
            @RequestParam int age,
            @RequestParam String email) {
        User user = service.getUserService().createUser(name, age, email);
        service.processRegistration(user);
        return "User added from parameters!";
    }

}
