package com.tracker.analytics.repository;

import com.tracker.analytics.exceptions.EntityNotFoundException;

import java.util.List;

public interface RedisRepository<T> {
    void save(T t, long id);

    List<T> findAll();

    T findById(Long id) throws IllegalStateException, EntityNotFoundException;
}
