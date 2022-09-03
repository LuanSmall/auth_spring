package com.web.employee.services;

import com.web.employee.dto.Role;
import com.web.employee.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserServices {
    User saveUser(User user);
    Role saveRole(Role roles);

    void  addRoleToUser(String username,String roleName);
    User getUser(String username);
    List<User> getUser();


}
