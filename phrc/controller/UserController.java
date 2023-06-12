package com.phrc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phrc.model.User;
import com.phrc.repository.UserRepository;

@RestController
@RequestMapping("/secure/rest")
public class UserController {
    @Autowired
	private UserRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    
    @PostMapping("/admin/add")
    public String addUserByAdmin(@RequestBody User user) {
    	String pwd=user.getPassword();
    	String enCryptPwd=bCryptPasswordEncoder.encode(pwd);
    	user.setPassword(enCryptPwd);
    	userRepository.save(user);
    	return "User Added Successfully";
    }
    
    @PreAuthorize("hasAnyRole('/Admin)")
    @GetMapping("/admin/all")
    public String securedHello() {
    	return "Secured Hello";
    }
}
