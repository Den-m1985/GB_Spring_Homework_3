package com.example.sem3HomeTask.controllers;

import com.example.sem3HomeTask.domain.User;
import com.example.sem3HomeTask.services.DataProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private DataProcessingService service;

    @GetMapping
    public List<String> getAllTasks() {
        List<String> tasks = new ArrayList<>();
        tasks.add("sort");
        tasks.add("filter");
        tasks.add("calc");
        return tasks;
    }

    /**
     * Endpoint to retrieve a sorted list of users based on age.
     * localhost:8080/tasks/sort
     *
     * @return A list of users sorted by age.
     */
    @GetMapping("/sort")
    public List<User> sortUsersByAge() {
        return service.sortUsersByAge(service.getRepository().getUsers());
    }


    /**
     * Endpoint for filtering users by age.
     * localhost:8080/tasks/filter/25
     *
     * @param age The age to filter users by.
     * @return A list of users matching the specified age.
     */
    @GetMapping("/filter/{age}")
    public List<User> filterUsersByAge(@PathVariable("age") int age) {
        List<User> users = service.getRepository().getUsers();
        return service.filterUsersByAge(users, age);
    }


    /**
     * Endpoint to calculate the average age of users.
     * localhost:8080/tasks/calc
     *
     * @return The average age of all users.
     */
    @GetMapping("/calc")
    public double calculateAverageAge() {
        List<User> users = service.getRepository().getUsers();
        return service.calculateAverageAge(users);
    }

}
