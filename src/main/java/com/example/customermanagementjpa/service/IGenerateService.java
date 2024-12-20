package com.example.customermanagementjpa.service;

import com.example.customermanagementjpa.model.Customer;

import java.util.List;

public interface IGenerateService<T> {
    List<T> findAll();

    void save(T t);

    T findById(int id);

    void remove(int id);

    boolean saveWithStoredProcedure(Customer customer);
}
