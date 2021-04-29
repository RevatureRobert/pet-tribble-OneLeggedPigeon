package com.revature.tribble.service;

import java.util.List;

public interface GenericService<T> {
    public List<T> getList();
    public T getById(int id);
    public void create(Object o);
    public void createOrUpdate(Object o);
    public void delete(Object o);
}
