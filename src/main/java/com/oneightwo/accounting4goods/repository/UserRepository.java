package com.oneightwo.accounting4goods.repository;

import com.oneightwo.accounting4goods.model.Role;
import com.oneightwo.accounting4goods.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, BigInteger> {

    Optional<User> getUserByUsername(String username);

    @Query(value = "select * from users where role_id = :roleId", nativeQuery = true)
    Optional<User> getUserByRoleId(@Param("roleId") BigInteger roleId);

    @Query(value =
            "select * " +
                    "from users " +
                    "where username = :surname " +
                    "and name = :name " +
                    "and patronymic = :patronymic",
            nativeQuery = true)
    Optional<User> getUserByFullName(@Param("surname") String surname,
                                     @Param("name") String name,
                                     @Param("patronymic") String patronymic);


}
