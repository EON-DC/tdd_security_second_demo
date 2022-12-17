package com.example.tdd_security_second_demo.authentication.rest;

import com.example.tdd_security_second_demo.authentication.domain.User;
import com.example.tdd_security_second_demo.authentication.service.UserService;
import com.example.tdd_security_second_demo.share.domain.NameValueList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping(path = "{id}")
    public User getUser(@PathVariable String id) {
        return userService.get(id);
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.add(user);
    }

    @PatchMapping(path = "{id}")
    public User updateUser(@PathVariable String id, NameValueList nameValueList) {
        return userService.update(id, nameValueList);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        userService.delete(id);
        return new ResponseEntity<>("User has deleted Ok", HttpStatus.OK);
    }
}
