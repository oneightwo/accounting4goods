package com.oneightwo.accounting4goods.service;

import com.oneightwo.accounting4goods.model.Type;
import com.oneightwo.accounting4goods.service.basic.BasicService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TypeService extends BasicService<Type> {

    @Query(value = "select * from types where name = :name",
            nativeQuery = true)
    public Optional<Type> getTypeByName(@Param("name") String name);

}
