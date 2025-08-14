package com.online.orderapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online.orderapp.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
