package vn.thai.example.querydsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.thai.example.querydsl.entity.User;

public interface UserRepository
    extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

}