package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/users")
    public UserResponse createNewUser(@RequestBody NewUserReques request){
        User user = new User();
        user.setName(request.getName());
        user.setAge(request.getAge());
        user = userRepository.save(user);
        return new UserResponse(user.getId(), user.getName() + user.getAge());
    }

    @GetMapping("/users")
    public PagingResponse getAllUser(@RequestParam (defaultValue = "1") int page,
                                     @RequestParam(name = "item_per_page", defaultValue = "10") int itemPerPage) {
        PagingResponse pagingResponse = new PagingResponse(page, itemPerPage);
        List<UserResponse> users = new ArrayList<>();
        users.add(new UserResponse(1, "User 1"));
        users.add(new UserResponse(2, "User 2"));

        pagingResponse.setUserResponse(users);
        return pagingResponse;
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

//    @PostMapping("/users")
//    public UserResponse createNewUser(@RequestBody NewUserReques request) {
//        return new UserResponse(0, request.getName() + request.getAge());
//    }
}
