package nl.robinlaugs.dealership.service;

import java.util.Collection;

public interface Service<T> {

    Collection<T> list();

    T get(String id);

    T create(T object);

    T update(T object);

    boolean delete(String id);

}
