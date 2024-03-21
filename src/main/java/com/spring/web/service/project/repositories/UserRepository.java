package com.spring.web.service.project.repositories;

import com.spring.web.service.project.model.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long>{

    Optional<UserEntity> findByEmail(String email);

    UserEntity findByFirstName(String firstName);


}
