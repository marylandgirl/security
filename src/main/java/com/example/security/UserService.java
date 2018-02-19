package com.example.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Transactional
@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepo;

    public UserService(UserRepository userRepo){
        this.userRepo=userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Set<GrantedAuthority> userAuthorities = new HashSet<>();
        User thisUser = userRepo.findUserByUserName(username);
        return new org.springframework.security.core.userdetails.User(thisUser.getUserName(),thisUser.getPassword(),
                grantedAuthorities(thisUser));
    }

    public Set<GrantedAuthority> grantedAuthorities(User thisUser) {
        Set<GrantedAuthority> userAuthorities = new HashSet<>();
        for ( Role eachRole: thisUser.getRoles()){
            userAuthorities.add(new SimpleGrantedAuthority(eachRole.getRoleName()));
        }
        return userAuthorities;
    }
}
