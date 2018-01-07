package com.aieme.pleasedheart.models.dao;

import java.util.List;

public interface Dao<T> {

    public int insert(T record);
    public void update(T record);
    public List<T> findAll();
    public T findById(int id);
    public void deleteAll();
    public void deleteById(int id);

}
