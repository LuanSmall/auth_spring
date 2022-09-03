package com.web.employee.userRepo;

import com.web.employee.dto.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long > {
    Role findByName(String role);
}
