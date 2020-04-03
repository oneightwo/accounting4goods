package com.oneightwo.accounting4goods.repository;

import com.oneightwo.accounting4goods.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, BigInteger> {

    @Query(value = "insert into sales (product_id, user_id, count, date) values (:productId, :userId, :count, :date) RETURNING product_id",
            nativeQuery = true)
    BigInteger save(@Param("productId") BigInteger productId,
              @Param("userId") BigInteger userId,
              @Param("date") LocalDateTime date,
              @Param("count") BigInteger count);

    @Query(value = "select * from sales where date >= current_date - 7 and user_id = :user", nativeQuery = true)
    List<Sale> getSaleByUserOfLastWeek(@Param("user") BigInteger userId);
}
