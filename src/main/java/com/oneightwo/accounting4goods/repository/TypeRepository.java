package com.oneightwo.accounting4goods.repository;

import com.oneightwo.accounting4goods.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface TypeRepository extends JpaRepository<Type, BigInteger> {

    public Optional<Type> getTypeByName (String name);
}
