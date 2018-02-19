package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String...strings) throws Exception{
        Role role = new Role();
        role.setRoleName("APPLICANT");
        roleRepository.save(role);

        role = new Role();
        role.setRoleName("ADMIN");
        roleRepository.save(role);

        role = new Role();
        role.setRoleName("EMPLOYER");
        roleRepository.save(role);

    }
}
