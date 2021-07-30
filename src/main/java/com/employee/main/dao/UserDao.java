package com.employee.main.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.employee.main.entity.User;

@Repository
public interface UserDao extends CrudRepository<User, String>{

}
