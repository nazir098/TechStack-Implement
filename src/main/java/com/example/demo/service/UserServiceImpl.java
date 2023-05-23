package com.example.demo.service;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.repository.RoleRepo;
import com.example.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
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
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user=userRepo.findByUsername(username);
      if(user==null)    {
          throw new UsernameNotFoundException("User not found in database ");
      }
        Collection<SimpleGrantedAuthority>authorities=new ArrayList<>();
      user.getRoles().forEach( role ->
      {
          authorities.add(new SimpleGrantedAuthority(role.getRole()));
      });

      return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }

    @Override
    public User saveUser(User user) {

       User username= userRepo.findByUsername(user.getUsername());
       if(username!=null)
           throw new RuntimeException("User allready exist");
       else
       {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
       }
    }

    @Override
    public Role saveRole(Role role) {

        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {

        User user=userRepo.findByUsername(username);
        Role role=roleRepo.findByRole(roleName);
        user.getRoles().add(role);
        user.setRoles(user.getRoles());
    }

    @Override
    public User getUser(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }


}
