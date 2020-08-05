package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @GetMapping("/users")
    @ResponseBody
    public List<UserResponse> getAllUser(@RequestParam (required = false, defaultValue = "1") String page,
                                         @RequestParam(required = false, defaultValue = "15") String itemPerPage) {
        List<UserResponse> users = new ArrayList<>();
        users.add(new UserResponse(1, "User 1"));
        users.add(new UserResponse(2, "User 2"));

        return users;
    }

    @GetMapping("/users/{id}")
    public UserResponse getUserById(@PathVariable int id){
        return new UserResponse(id, "User "+id);
    }

    @GetMapping("/test")
    public String getUrlByParam(@RequestParam (required = false, defaultValue = "1") String page,
                                @RequestParam(required = false, defaultValue = "15") String itemPerPage){
        return "Page: " + page + " Item per page: " + itemPerPage;
    }
}
