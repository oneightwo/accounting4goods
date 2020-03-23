package com.oneightwo.accounting4goods.repository;

import com.oneightwo.accounting4goods.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, BigInteger> {

    @Query(value = "select * from roles where role = :role", nativeQuery = true)
    Role getRoleByString(@Param("role") String role);
}
