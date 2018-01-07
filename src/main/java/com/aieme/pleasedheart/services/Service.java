package com.aieme.pleasedheart.services;

import java.util.List;

public interface Service<T> {

    public int insert(T record);
    public void update(T record);
    public List<T> findAll();
    public T findById(int id);
    public void delete(int id);
    public void deleteAll();

}
