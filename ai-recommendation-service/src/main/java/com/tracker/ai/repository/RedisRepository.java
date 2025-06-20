package com.tracker.ai.repository;

import java.util.List;

public interface RedisRepository<T> {
    void save(T t, long id);

    List<T> findAll();

    T findById(Long id) throws IllegalStateException;
}
