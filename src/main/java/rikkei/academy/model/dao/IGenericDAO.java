package rikkei.academy.model.dao;

import java.util.List;

public interface IGenericDAO<T, ID> {
    List<T> findAll();
    T findById(ID id) ;
    Boolean create(T t);
    Boolean delete(ID id);
    Boolean update(T t,ID id);
}
