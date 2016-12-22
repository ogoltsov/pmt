package com.epam.pmt.repository;

import java.util.Collection;

public interface Repository<T> {

    Collection<T> findAll();

    T findById(int id);

    boolean save(T t);

    boolean delete(T t);

}
