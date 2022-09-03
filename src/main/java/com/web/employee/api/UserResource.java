package com.web.employee.api;

import com.web.employee.dto.Role;
import com.web.employee.dto.User;
import com.web.employee.services.UserServices;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserResource {
    @Autowired
    private final UserServices userServices;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUser(){
        return  ResponseEntity.ok().body(userServices.getUser());

    }
    @PostMapping("/users/save")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/user/save").toUriString());
        return  ResponseEntity.created(uri).body(userServices.saveUser(user));

    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/role/save").toUriString());
        return  ResponseEntity.created(uri).body(userServices.saveRole(role));

    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/role/save").toUriString());
        userServices.addRoleToUser(form.getUsername(), form.getRolename());
        return  ResponseEntity.ok().build();

    }
    @Data
    class RoleToUserForm {
        private String username;
        private String rolename;
    }
}
