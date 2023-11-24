package rikkei.academy.model.service;

import java.util.List;

public interface IGennericService<T, ID>{
    List<T> findAll();
    T findById(ID id) ;
    Boolean create(T t);
    Boolean delete(ID id);
    Boolean update(T t,ID id);
    T logon(T t) ;
}
