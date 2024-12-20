package com.example.customermanagementjpa.service;

import java.util.List;

public interface IGenerateService<T> {
    List<T> findAll();

    void save(T t);

    T findById(int id);

    void remove(int id);
}
