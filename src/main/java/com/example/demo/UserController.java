package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/users")
    public UsersResponse createNewUser(@RequestBody NewUserRequest request){
        User user = new User();
        user.setName(request.getName());
        user.setAge(request.getAge());
        user = userRepository.save(user);
        return new UsersResponse(user.getId(), user.getName() + user.getAge());
    }

    @GetMapping("/users")
    public PagingResponse getAllUser(@RequestParam(defaultValue = "1") int page,
                                     @RequestParam(name = "item_per_page", defaultValue = "10") int itemPerPage) {
        PagingResponse pagingResponse = new PagingResponse(page, itemPerPage);
        List<UsersResponse> usersResponseList = new ArrayList<>();

        Iterable<User> users = userRepository.findAll();
        for (User user: users) {
            usersResponseList.add(new UsersResponse(user.getId(), user.getName(), user.getAge()));
        }

        pagingResponse.setUsersResponse(usersResponseList);
        return pagingResponse;
    }

    @GetMapping("/users/{id}")
    public UsersResponse getUserById(@PathVariable int id){

        Optional<User> users = userRepository.findById(id);
        return new UsersResponse(users.get().getId(), users.get().getName(), users.get().getAge());

    }

    @PostMapping ("/users1")
    public String createNewUserWithFormData (NewUserRequest request){
        return request.getName()+request.getAge();
    }

    @GetMapping("/test")
    public String getUrlByParam(@RequestParam (required = false, defaultValue = "1") String page,
                                @RequestParam(required = false, defaultValue = "15") String itemPerPage){
        return "Page: " + page + " Item per page: " + itemPerPage;
    }

}
