package com.fastjee.usercenter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestUser {

    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = bCryptPasswordEncoder.encode("password");
        System.out.println("password = " + password);
    }
}
