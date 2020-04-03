package com.oneightwo.accounting4goods.service.basic;

import java.util.List;

public interface BasicService<T> {

    List<T> getAll();

    T save(T t);

    void delete(T t);

    T update(T t);
}
