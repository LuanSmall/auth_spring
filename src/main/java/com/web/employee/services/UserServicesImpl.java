package com.web.employee.services;

import com.web.employee.dto.Role;
import com.web.employee.dto.User;
import com.web.employee.userRepo.RoleRepo;
import com.web.employee.userRepo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional @Slf4j

public class UserServicesImpl implements  UserServices , UserDetailsService {
    private  final UserRepo userRepo;
    private  final RoleRepo roleRepo;

    private  final PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        log.info("Saving new user{} to the database",user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role roles) {
        log.info("Saving new role{} to the database",roles.getName());
        return roleRepo.save(roles);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role{} to name{} ",username,roleName);
        User user = userRepo.findByName(username);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);

    }

    @Override
    public User getUser(String username) {
        log.info("Fetching  user{} to the database",username);
        return userRepo.findByName(username);
    }

    @Override
    public List<User> getUser() {
        log.info("Fetching all user");
        return userRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByName(username) ;
        if(user==null){
            log.error("User is empty");
            throw  new UsernameNotFoundException("User not found");

        }else{
            log.info("User not found db:{}",username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }
}
