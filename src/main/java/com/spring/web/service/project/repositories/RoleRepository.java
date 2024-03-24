package com.spring.web.service.project.repositories;

import com.spring.web.service.project.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    boolean existsById(Long id);

    Optional<Role> findByName(String name);

}
