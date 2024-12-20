package com.example.customermanagementjpa.repository;

import com.example.customermanagementjpa.model.Customer;

import java.util.List;

public interface IGenerateRepository<T> {
    List<T> findAll();

    T findById(int id);

    void save(T t);

    void remove(int id);

    boolean saveWithStoredProcedure(Customer customer);
}