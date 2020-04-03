package com.oneightwo.accounting4goods.service.impl;

import com.oneightwo.accounting4goods.model.Type;
import com.oneightwo.accounting4goods.repository.TypeRepository;
import com.oneightwo.accounting4goods.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Override
    public List<Type> getAll() {
        return typeRepository.findAll();
    }

    @Override
    public Type save(Type type) {
        return typeRepository.save(type);
    }

    @Override
    public void delete(Type type) {
        typeRepository.delete(type);
    }

    @Override
    public Type update(Type type) {
        return typeRepository.save(type);
    }

    @Override
    public Optional<Type> getTypeByName(String name) {
        return typeRepository.getTypeByName(name);
    }
}
