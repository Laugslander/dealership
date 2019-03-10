package nl.robinlaugs.dealership.data.dao;

import java.util.Collection;

public interface Dao<T> {

    Collection<T> list();

    T get(String id);

    T save(T object);

    T update(T object);

    boolean delete(String id);

}
