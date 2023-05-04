package com.example.controller;

import com.example.entity.User;
import com.example.entity.enums.StatusEnum;
import com.example.entity.projection.UserProjection;
import com.example.entity.request.UserReq;
import com.example.entity.response.Pagination;
import com.example.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserController {
    private UserService userService;
    @Autowired
    private UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public String addUser(@RequestBody UserReq req){
        // TODO: Prepare User Object
        User user = new User();
//        user.setUsername(req.getUsername());
//        user.setPassword(req.getPassword());
//        user.setEmail(req.getEmail());
        BeanUtils.copyProperties(req, user);
        User us1 = userService.addUser(user);
        return "added user";
    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable Long id){
        this.userService.deleteById(id);
        return "delete user";
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable Long id , @RequestBody UserReq req){
        User user = new User();
        BeanUtils.copyProperties(req, user);
        user.setId(id);
        userService.updateUser(user);
        return "Update user";
    }


    @GetMapping("/{id}")
    public Map<String, Object> findUserById(@PathVariable Long id){
        Map<String, Object> map = new HashMap<>();
        UserProjection user = this.userService.findUserProjectionById(id);
        if(user != null){
            map.put("message", "Success");
            map.put("data", user);
        }else {
            map.put("message", "No Data!");
        }
        return map;
    }

    @GetMapping("/")
    public Map<String, Object> findUserByUsernameContaining(
            @RequestParam(value = "username", required = false, defaultValue = "") String username,
            Pagination pagination){
        Map<String, Object> map = new HashMap<>();
        List<UserProjection> user = this.userService.findUserProjectionByUsernameContainingIgnoreCase(username, pagination);
        if(user != null){
            map.put("message", "Success");
            map.put("data", user);
            map.put("pagination", pagination);
        }else {
            map.put("message", "No Data!");
        }
        return map;
    }

    @GetMapping("/count")
    public Map<String, Object> countUserByStatus(){
        Map<String, Object> map = new HashMap<>();
        long user = this.userService.countUserByStatus( StatusEnum.ACT);
        map.put("data", user);
        map.put("message", "success");
        return map;
    }
}
