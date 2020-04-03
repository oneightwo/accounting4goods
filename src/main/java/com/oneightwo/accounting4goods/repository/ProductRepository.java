package com.oneightwo.accounting4goods.repository;

import com.oneightwo.accounting4goods.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, BigInteger> {

    @Query(value = "select * from products p, types t where t.id = p.type_id and t.name = :t_name and p.name ilike :p_name || '%'",
            nativeQuery = true)
    List<Product> getFilteredProductByNameAndType(@Param("p_name") String productName, @Param("t_name") String typeProduct);

    @Query(value = "select * from products p where p.name ilike :p_name || '%'",
            nativeQuery = true)
    List<Product> getFilteredProductByName(@Param("p_name") String productName);

    @Query(value = "select * from products p, types t where t.id = p.type_id and t.name = :t_name",
            nativeQuery = true)
    List<Product> getFilteredProductByType(@Param("t_name") String typeProduct);

    @Query(value = "update products set count = (select count from products where id = :id) - :count where id = :id RETURNING id",
            nativeQuery = true)
    BigInteger updateCountProduct(@Param("id") BigInteger id,
                                  @Param("count") BigInteger count);
}
