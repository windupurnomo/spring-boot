package com.zcodegroup.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zcodegroup.ws.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
