package com.web.employee.userRepo;

import com.web.employee.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByName(String name);

}
