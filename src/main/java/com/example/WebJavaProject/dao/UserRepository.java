package com.example.WebJavaProject.dao;

import com.example.WebJavaProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public List<User> findAllByOrderByLastNameAsc();

    public User findUserByLogin(String login);

}
