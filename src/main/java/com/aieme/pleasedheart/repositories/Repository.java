package com.aieme.pleasedheart.repositories;

import java.util.List;

public interface Repository<T> {

    public int insert(T record);
    public void update(T record);
    public List<T> findAll();
    public T findById(int id);
    public void deleteAll();
    public void deleteById(int id);

}
